<script setup>
import { RouterLink, RouterView } from 'vue-router'
import UserProfile from "@/components/userProfile.vue";
import HelloWorld from './components/HelloWorld.vue'

const username = "Byron";
const userEmail = "Byron.chen@mail.mcgill.ca";

</script>

<template>
  <header>
    <!-- The navigation bar -->
    <nav>
      <div>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
        <RouterLink to="/events">Events</RouterLink>
      </div>

      <UserProfile :username="username" :userEmail="userEmail" />
    </nav>
  </header>

  <!-- The routed views -->
  <RouterView />
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh; /* Sets the maximum height of the header to 100% of the viewport height */
  padding: 2rem; /* Adds padding to the top and bottom of the header */
}

nav {
  display: flex;
  position: fixed; /* Keeps it at the top even when scrolling */
  align-items: center; /* Align items vertically */
  top: 0;
  width: 80%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem; /* Pushes it down from the top with rem as the unit meaning it's relative to the font size of the root element */
  z-index: 1000; /* Ensures it stays above other elements */
  justify-content: space-between;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>