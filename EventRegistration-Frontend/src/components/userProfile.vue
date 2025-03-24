<script setup>
import { defineProps } from 'vue';
import {useAuthStore} from "@/stores/authStores.js";

const props = defineProps({
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
    <p id="user-name"> {{authStore.user.name}}</p>
    <p id="user-email"> {{authStore.user.email}}</p>

    <button v-if="!authStore.user.isAuthenticated" @click="loginAction()"> Login </button>
    <button v-else @click="logoutAction()"> Logout </button>
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