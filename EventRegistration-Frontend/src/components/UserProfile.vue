<script setup>

const props = defineProps( {
  username: String,
  userEmail: String,
});

</script>

<template>
  <div class="user-profile">
    <p id="user-name"> {{ username }} </p>
    <p id="user-email"> {{ userEmail }} </p>
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