<script setup>
import router from '@/router';
import { onMounted, ref } from 'vue';

const url = "http://localhost:8080/usuario/login";

const usuario = ref("");
const senha = ref("");

function login() {
    const data = {usuario : usuario.value ,senha : senha.value}

    
    axios.post(url, data).then(response => {
        let token = response.data.token;
        if (token) {
            localStorage.setItem('token',token);
        }
        router.push('/projeto')
        
    })
    
}


</script>

<template>
    <form @submit.prevent="login">
        <label>
            <span>usuanrio</span>
            <input type="text" v-model="usuario">
        </label>
        <label>
            <span>senha</span>
            <input type="password" v-model="senha">
        </label>
        <input type="submit" value="Enviar">
    </form>
</template>