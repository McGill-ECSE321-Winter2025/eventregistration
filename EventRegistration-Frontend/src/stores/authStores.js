import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', () => {
    const user = ref({
        name: null,
        email: null,
        isAuthenticated: false,
    })

    function login(name, email, pwd) {
        if (!pwd) return;
        // checking at backend
        user.value.name = name;
        user.value.email = email;
        user.value.isAuthenticated = true;
    }

    function logout() {
        user.value.name = null;
        user.value.email = null;
        user.value.isAuthenticated = false;
    }

    return { user, login, logout }
})
