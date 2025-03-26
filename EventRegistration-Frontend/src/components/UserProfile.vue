<script setup>

import {useAuthStore} from "@/stores/authStore.js";

const props = defineProps( {
  username: String,
  userEmail: String,
});

const authStore = useAuthStore();

function loginAction() {
  authStore.login(props.username, props.userEmail, 1);
}

function logoutAction() {
  authStore.logout();
}

</script>

<template>
  <div class="user-profile">
    <button v-if="!authStore.user.isAuthenticated" @click="loginAction()"> Login </button>
    <button v-else @click="logoutAction()"> Logout </button>
    <p id="user-name"> {{ authStore.user.username }} </p>
    <p id="user-email"> {{ authStore.user.userEmail }} </p>
  </div>
</template>

<style scoped>

.user-profile {
  display: flex;
  flex-direction: row;
  padding: 10px;
  gap: 10%;
  color: saddlebrown;
}

</style>