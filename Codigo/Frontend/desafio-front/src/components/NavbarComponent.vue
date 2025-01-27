<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
const props = defineProps(['place'])
const emit = defineEmits(['pesquisarNome']);

const nomeProjeto = ref("");

function search(nome) {
    emit('pesquisarNome', nome)
}
</script>
<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <RouterLink class="navbar-brand" to="/projeto">Desafio</RouterLink>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <RouterLink class="nav-link" to="/Projeto">Inicio <span class="sr-only">(projeto)</span></RouterLink>
                </li>
                <li class="nav-item">
                    <RouterLink class="nav-link" to="/Projeto">Responsavel Tarefa <span class="sr-only">(projeto)</span></RouterLink>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" @submit.prevent="search(nomeProjeto)">
                <input class="form-control mr-sm-2" type="search" :placeholder="place" aria-label="Search" v-model="nomeProjeto">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
            </form>
        </div>
    </nav>
</template>