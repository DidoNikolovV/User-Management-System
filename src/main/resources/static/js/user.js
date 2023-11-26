const usersCtr = document.getElementById('usersContainer');
const searchForm = document.getElementById("searchForm");
const nextBtn = document.getElementById("next");

searchForm.addEventListener('submit',  (e) => {
    e.preventDefault();

    usersCtr.innerHTML = '';
    const searchParam = document.getElementById('searchParam').value;

    loadUsers(searchParam);
})

nextBtn.addEventListener("click", loadUsers);


const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content



function usersAsHTML(user) {
    let userHTML = `<tr data-id="${user.id}">\n`
    userHTML += `<td>${user.firstName}</td>\n`
    userHTML += `<td>${user.lastName}</td>\n`
    userHTML += `<td>${user.dateOfBirth}</td>\n`
    userHTML += `<td>${user.phoneNumber}</td>\n`
    userHTML += `<td>${user.email}</td>\n`
    userHTML += `<td><a href="/users/edit/${user.id}" class="btn btn-info">Edit</a></td>\n`
    userHTML += `<td><button onclick="deleteUser(${user.id})" class="btn btn-danger">Delete</button></td>\n`
    userHTML += '</tr>\n';

    return userHTML;
}

async function deleteUser(userId) {
    const userRow = document.querySelector(`#usersContainer tr[data-id="${userId}"]`);
    fetch(`http://localhost:8080/api/users/${userId}`, {
        method: 'DELETE',
        headers: {
            [csrfHeaderName]: csrfHeaderValue
        }
    }).then(res => res.json())
        .then(data => {
            usersCtr.removeChild(userRow);
        })
}

async function updateUser() {
    const formData = new FormData(editForm);

    fetch(`http://localhost:8080/api/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify(Object.fromEntries(formData)),
    }).then(res => {
        if(!res.ok) {
            throw new Error('Failed to update user');
        }
        return res.json();
    }).then(data => {
        console.log('User updated successfully:', data);
    }).catch(error => {
        console.error('Error updating user:', error);
    });
}

async function loadUsers(searchParam = '', page = 0, size = 3) {

    const queryParams = searchParam ? `?searchParam=${encodeURIComponent(searchParam)}` : '';
    const pageParam = searchParam ? `&page=${encodeURIComponent(page)}` : `?page=${encodeURIComponent(page)}`;
    const sizeParam = `&size=${encodeURIComponent(size)}`;


    fetch(`http://localhost:8080/api/users${queryParams}${pageParam}${sizeParam}`, {
        headers: {
            "Accept": "application/json"
        }
    }).then(res => res.json())
        .then(data => {
            for(let user of data) {
                usersCtr.innerHTML += usersAsHTML(user)
            }
        }).catch(error => {
        console.error('Error fetching users: ', error);
    })
}

loadUsers();

// fetch(`http://localhost:8080/api/users`, {
//     headers: {
//         "Accept": "application/json"
//     }
// }).then(res => res.json())
//     .then(data => {
//         for(let user of data) {
//             console.log("In GET REQUEST")
//             usersCtr.innerHTML += usersAsHTML(user)
//         }
//     }).catch(error => {
//     console.error('Error fetching users: ', error);
// })


