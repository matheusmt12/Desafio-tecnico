<script setup>
import moment from 'moment'
const props = defineProps(['dados', 'titulos', 'tarefas', 'statusAlterar', 'finalizarProjeto']);
const emits = defineEmits(['funcTarefas', 'funcStatus', 'funcFinalizar']);



// Função para dividir strings por letras maiúsculas
function splitByUppercase(str) {
    if (typeof str === 'string') {

        return str.split(/(?=[A-Z])|_/).join(' ').toUpperCase();

    }

    return str.toUpperCase();
}

// funções para passar na tabela e retornar o valor de uma linha

function funcTarefa(id) {
    emits('funcTarefas', id);
}

function funcStatus(obj) {
    emits('funcStatus', obj);
}

function funcFinalizar(obj) {
    emits('funcFinalizar', obj);
}
</script>

<template>
    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th v-for="t in titulos" class="text-captalize">
                        <span v-if="t === 'data_termino'">DATA DE TÉRMINO</span>
                        <span v-else>{{ splitByUppercase(t) }}</span>
                    </th>
                    <th>AÇÕES</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="dado in dados">
                    <td v-for="chave in titulos " :key="chave">
                        <span v-if="chave == 'data_termino'">{{ moment(dado[chave]).format('DD/MM/YYYY') }}</span>
                        <span v-else>{{ dado[chave] }}</span>
                    </td>
                    <td v-if="finalizarProjeto"><button class="btn btn btn-light"
                            @click="funcFinalizar(dado)">Status</button></td>
                    <td v-if="tarefas">
                        <samp v-if="dado.status === 'FINALIZADO'">
                            <button data-bs-toggle="tooltip" title="O projeto ja foi finalizado" class="btn btn-primary" @click="funcTarefa(dado.id)" disabled>Tarefas</button>
                        </samp>
                        <span v-else>
                            <button class="btn btn-primary" @click="funcTarefa(dado.id)">Tarefas</button>
                        </span>
                    </td>
                    <td v-if="statusAlterar"><button class="btn btn-primary" @click="funcStatus(dado)">Status</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
