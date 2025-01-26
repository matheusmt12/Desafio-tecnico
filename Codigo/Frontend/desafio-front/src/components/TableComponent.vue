<script setup>
import moment from 'moment'
const props = defineProps(['dados', 'titulos','tarefas','statusAlterar']);
const emits = defineEmits(['funcTarefas', 'funcStatus']);



// Função para dividir strings por letras maiúsculas
function splitByUppercase(str) {
    if (typeof str === 'string') {

        return str.split(/(?=[A-Z])|_/).join(' ').toUpperCase();

    }

    return str.toUpperCase(); 
}

function formatarData(data) {
    return moment(data).format('DD/MM/YYYY');
}

// funções para passar na tabela e retornar o valor de uma linha
function funcTarefa(id) {
    emits('funcTarefas', id);
}

function funcStatus(obj) {
 emits('funcStatus', obj); 
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
                <td v-if="statusAlterar"><button class="btn btn-primary" @click="funcStatus(dado)">Status</button></td>
            </tr>
        </tbody>
    </table>
</template>
