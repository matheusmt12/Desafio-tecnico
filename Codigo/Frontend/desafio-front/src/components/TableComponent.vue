<script setup>
import moment from 'moment'
const props = defineProps(['dados', 'titulos','tarefas']);
const emits = defineEmits(['funcTarefas']);



// Função para dividir strings por letras maiúsculas
function splitByUppercase(str) {
    if (typeof str === 'string') {

        return str.split(/(?=[A-Z])|_/).join(' ').toUpperCase();

    }

    return str.toUpperCase(); 
}

function funcTarefa(id) {
    emits('funcTarefas', id)
}

function formatarData(data) {
    return moment(data).format('DD/MM/YYYY')
}

</script>

<template>
    <table class="table">
        <thead>
            <tr>
                <th v-for="t in titulos" class="text-captalize"><span>{{ splitByUppercase(t) }}</span></th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="dado in dados">
                <td v-for="chave in titulos " :key="chave">
                    <span v-if="chave == 'data_termino'">{{ formatarData(dado[chave]) }}</span>
                    <span v-else>{{ dado[chave] }}</span>
                </td>
                <td v-if="tarefas"> <button class="btn btn-primary" @click="funcTarefa(dado.id)">Tarefas</button></td>
            </tr>
        </tbody>
    </table>
</template>
