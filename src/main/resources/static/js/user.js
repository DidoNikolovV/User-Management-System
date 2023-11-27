const usersCtr = document.getElementById('usersContainer');
const searchForm = document.getElementById("searchForm");

const nextBtn = document.getElementById("next");

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

searchForm.addEventListener('submit',  (e) => {
    e.preventDefault();

    usersCtr.innerHTML = '';
    const searchParam = document.getElementById('searchParam').value;

    loadUsers(searchParam);
})

nextBtn.addEventListener("click", loadUsers);


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



function usersAsHTML(user) {
    let userHTML = `<tr data-id="${user.id}">\n`
    userHTML += `<td>${user.firstName}</td>\n`
    userHTML += `<td>${user.lastName}</td>\n`
    userHTML += `<td>${user.dateOfBirth}</td>\n`
    userHTML += `<td>${user.phoneNumber}</td>\n`
    userHTML += `<td>${user.email}</td>\n`
    userHTML += `<td><a href="/users/edit/${user.id}" class="btn btn-info">Edit</a></td>\n`
    userHTML += `<td><button onclick="deleteUser(${user.id})" class="btn btn-danger">Delete</button></td>\n`
    userHTML += `<td><a href="/users/${user.id}" class="btn btn-info">Details</a></td>\n`
    userHTML += '</tr>\n';

    return userHTML;
}



