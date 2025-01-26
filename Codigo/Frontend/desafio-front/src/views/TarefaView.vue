<script setup>
import router from '@/router';
import { onMounted, ref } from 'vue';

const url = "http://localhost:8080/tarefa";
const idProjeto = router.currentRoute.value.params.id;
function buscarTarefas() {
    
    console.log(idProjeto);
    
}


onMounted(() =>{
    buscarTarefas();
})
</script>

<template>

    <h1>esta aqui</h1>
</template>