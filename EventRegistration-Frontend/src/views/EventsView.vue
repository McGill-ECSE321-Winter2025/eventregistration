<script setup>
import {computed, onMounted, ref} from "vue";

import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8080",
});

let events = ref([]);

let newEventName = ref(null);
let newEventStartTime = ref(null);
let newEventEndTime = ref(null);
let newEventDate = ref(null);
let newEventRegLimit = ref(null);
let newEventLocation = ref(null);

onMounted(async () => {
  try {
    const response = await axiosClient.get("/events")
    events.value = response.data.events;

  } catch (e) {
    console.error(e);
  }
})

async function createEvent() {
  const newEvent = {
    type: "IN_PERSON",
    name: newEventName.value,
    date: newEventDate.value,
    startTime: newEventStartTime.value,
    endTime: newEventEndTime.value,
    regLimit: newEventRegLimit.value,
    address: newEventLocation.value,
  };

  try {
    await axiosClient.post("/events", newEvent);
  } catch (e) {
    console.error(e);
  }

  events.value.push(newEvent);
}

function clearForm() {
  newEventName.value = null;
  newEventStartTime.value = null;
  newEventEndTime.value = null;
  newEventRegLimit.value = null;
  newEventDate.value = null;
  newEventLocation.value = null;
}

function deleteAllEvents() {
  events.value = [];
}

const isCreateBtnDisabled = computed(() => {
  return !newEventName.value || !newEventDate.value || !newEventRegLimit.value;
})

// // DOMContentLoaded event is used to make sure that the script is executed after the HTML is completely loaded
// // The event listener is added to the button with the id create-btn
// window.addEventListener("DOMContentLoaded", () => {
//   const createBtn = document.getElementById("create-btn");
//
//   createBtn.addEventListener("click", () => {
//     // Get the values of the input fields
//     const name = document.getElementById("name-input").value;
//     const date = document.getElementById("date-input").value;
//     const regLimit = document.getElementById("limit-input").value;
//
//     // Create a new table row element
//     const tr = document.createElement("tr");
//     tr.innerHTML = `<td>${name}</td><td>${date}</td><td>${regLimit}</td>`;
//
//     // Get the table body element and append the new table row element
//     const tbody = document.getElementById("event-tbody");
//     tbody.appendChild(tr);
//   })
//
//   const clearBtn = document.getElementById("clear-btn");
//
//   clearBtn.addEventListener("click", () => {
//     const inputFields = document.getElementsByClassName("input-field");
//     for (let i = 0; i < inputFields.length; i++) {
//       inputFields[i].value = "";
//     }
//   })
//
//   const deleteAllBtn = document.getElementById("delete-btn");
//
//   deleteAllBtn.addEventListener("click", () => {
//     const tbody = document.getElementById("event-tbody");
//     // Delete all the rows except the header row
//     tbody.innerHTML = "<tr><th>Name</th><th>Date</th><th>Registration Limit</th></tr>";
//   })
// })
</script>

<template>

  <!DOCTYPE html> <!-- Tell the browser this is an HTML5 file -->

  <html lang="en"> <!-- Start of the HTML document and tell the browser the language of the document -->

  <head>  <!-- A head tag that contains metadata about the document -->
    <meta charset="UTF-8"> <!-- Set the character set to UTF-8 -->
    <title>Vanilla</title> <!-- Set the title of the page -->
  </head>

  <body>

  <h1>Hello World</h1> <!-- A heading tag that contains the title of the page -->

  <h2>Schedule New In-Person Event</h2> <!-- A heading tag that contains the title of the section -->

  <p>This is a running example</p>

  <!-- A container that contains the input fields and buttons -->
  <!-- A container is a block-level element that contains other elements and is used to group elements together to style them with CSS -->
  <div>
    <!-- Input fields -->
    <input type="text" v-model="newEventName" class="input-field" id="name-input" placeholder="Name"/>
    <input type="date" v-model="newEventDate" class="input-field" id="date-input" placeholder="Date"/>
    <input type="time" v-model="newEventStartTime" class="input-field" placeholder="Start Time"/>
    <input type="time" v-model="newEventEndTime" class="input-field" placeholder="End Time"/>
    <input type="text" v-model="newEventRegLimit" class="input-field" id="limit-input" placeholder="Registration Limit"/>
    <input type="text" v-model="newEventLocation" class="input-field" placeholder="Location"/>

    <!-- select -->
    <select>
      <option value="online">Online</option>
      <option value="in-person">In-Person</option>
    </select>

    <!-- checkbox -->
<!--    <checkbox>-->
<!--      <input type="checkbox" id="is-virtual" name="is-virtual" value="virtual"> &lt;!&ndash; A checkbox input field &ndash;&gt;-->
<!--      <label for="is-virtual">Virtual</label><br> &lt;!&ndash; A label for the checkbox input field &ndash;&gt;-->
<!--    </checkbox>-->

    <!-- Buttons -->
    <button @click="createEvent()" v-bind:disabled="isCreateBtnDisabled" id="create-btn">Create Event</button>
    <button @click="clearForm()" id="clear-btn" class="danger-btn">Clear</button>
    <button @click="deleteAllEvents()" id="delete-btn" class="danger-btn">Delete All Events</button>

  </div>

  <h2>Events</h2>

  <p>Here are the events!</p>

  <!-- A table that contains the events -->
  <table>
    <tbody id="event-tbody">
    <!-- A table head that contains the column names -->
    <tr>
      <th>Name</th>
      <th>Date</th>
      <th>Registration Limit</th>
    </tr>
    <!-- A table row that contains the event data -->
    <tr v-for="e in events">
      <td> {{ e.name }} </td>
      <td> {{ e.date }} </td>
      <td> {{ e.regLimit }} </td>
    </tr>

    </tbody>
  </table>

  </body>

  </html>

</template>

<style scoped>
  /* This file contains the CSS for the Vanilla-Frontend */
  /* It is used to style the HTML elements of the application */
  html {
    /* Set the background color of the page */
    background-color: antiquewhite;
    /* Set the font family of the page, the comma separated list is used to provide fallback fonts */
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }

  img {
    /* Set the width of the image */
    width: 400px;
    /* Set the border radius of the image */
    border-radius: 20px;
    /* Set the object fit of the image */
    object-fit: cover;
    /* Set the float of the image */
    float: left;
    /* Set the padding and margin of the image */
    padding: 1em;
    margin: 1em;
  }

  h2 {
    /* Set the decoration of the heading */
    text-decoration: underline;
  }

  p {
    /* Set the color of the paragraph */
    color: saddlebrown;
  }

  table {
    border-collapse: collapse;
  }

  td, th {
    /* Set the padding of the table cells with em units meaning relative to the font size of the element */
    padding: 0.5em;
    /* Set the border of the table cells */
    border: 1px solid black;
  }

  /* Set the styling of the danger button class (by class) */

  .danger-btn {
    color: red;
    border: 1px solid red;
  }

  /* Set the styling of the create button (by id) */
  #create-btn {
    color: green;
    border: 1px solid green;
  }
</style>