// DOMContentLoaded event is used to make sure that the script is executed after the HTML is completely loaded
// The event listener is added to the button with the id create-btn
window.addEventListener("DOMContentLoaded", () => {
    const createBtn = document.getElementById("create-btn");

    createBtn.addEventListener("click", () => {
        // Get the values of the input fields
        const name = document.getElementById("name-input").value;
        const date = document.getElementById("date-input").value;
        const regLimit = document.getElementById("limit-input").value;

        // Create a new table row element
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${name}</td><td>${date}</td><td>${regLimit}</td>`;

        // Get the table body element and append the new table row element
        const tbody = document.getElementById("event-tbody");
        tbody.appendChild(tr);
    })

    const clearBtn = document.getElementById("clear-btn");

    clearBtn.addEventListener("click", () => {
        const inputFields = document.getElementsByClassName("input-field");
        for (let i = 0; i < inputFields.length; i++) {
            inputFields[i].value = "";
        }
    })

    const deleteAllBtn = document.getElementById("delete-btn");

    deleteAllBtn.addEventListener("click", () => {
        const tbody = document.getElementById("event-tbody");
        // Delete all the rows except the header row
        tbody.innerHTML = "<tr><th>Name</th><th>Date</th><th>Registration Limit</th></tr>";
    })
})