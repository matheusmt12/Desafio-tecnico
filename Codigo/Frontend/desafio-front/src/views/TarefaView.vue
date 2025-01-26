<script setup>
//imports
import router from '@/router';
import { onMounted, ref } from 'vue';
import NavbarComponent from '@/components/NavbarComponent.vue';
import CardComponent from '@/components/CardComponent.vue';
import TableComponent from '@/components/TableComponent.vue';
import ModalComponent from '@/components/ModalComponent.vue';
import InputComponent from '@/components/InputComponent.vue';
import StatusTarefaEnum from '@/enums/StatusTarefaEnum';


//variáveis
const url = "http://localhost:8080/tarefa";
const idProjeto = router.currentRoute.value.params.id;
const dadosTarefas = ref([]);
const statusAlterar = ref(true)
const tarefa = ref(null);

//Modal funções e variáveis 
const visivelModal = ref(false);
const visivelModalStatus = ref()
function abrirModal() {
    visivelModal.value = true;
}

function fecharModal() {
    visivelModal.value = false;
    visivelModalStatus.value = false;
}

function mudarStatus(obj) {
    tarefa.value = obj;
    visivelModalStatus.value = true
}

//fim modal

//funções
function token() {
    return localStorage.getItem('token');
}
function buscarTarefas() {

    axios.get(url + '/' + idProjeto, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {
        dadosTarefas.value = response.data;
        console.log(dadosTarefas.value);

    });
}

function alterarStatus() {
    let status = document.getElementById('statusTarefa').value;
    console.log(status);
    
}



onMounted(() => {
    buscarTarefas();
})
</script>

<template>
    <NavbarComponent></NavbarComponent>
    <CardComponent titulo="Tarefas">
        <template v-slot:conteudo>
            <span v-if="dadosTarefas.length === 0">
                <p>Esse Projeto não Possui nunhuma tarefa</p>
            </span>
            <span v-else>
                <TableComponent @funcStatus="mudarStatus" :status-alterar="statusAlterar" :dados="dadosTarefas"
                    :titulos="['titulo', 'nome_responsavel', 'status']"></TableComponent>
            </span>
        </template>
        <template v-slot:footer>
            <button class="btn btn-primary">Adicionar</button>
        </template>
    </CardComponent>

    <ModalComponent :visivel="visivelModalStatus" titulo="Alterar Status">
        <template v-slot:conteudo>
            <div class="row">
                <div class="col">
                    <InputComponent label="Titulo">
                        <label class="form-control">{{ tarefa.titulo }}</label>
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent label="Descrição">
                        <label class="form-control">{{ tarefa.descricao }}</label>
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                    <div class="col">
                        <InputComponent label="Prazo">
                            <label class="form-control">Prazo da tarefa: {{ tarefa.prazo }}</label>
                        </InputComponent>
                    </div>
                    <div class="col">
                        <InputComponent label="Responsavel">
                        <label class="form-control">{{ tarefa.nome_responsavel }}</label>
                        </InputComponent>
                    </div>
                </div>
                <h5>Alterar Status</h5>
                <div class="row">
                    <div class="col">
                        <select name="statusTarefa" id="statusTarefa">
                            <option v-for="status in StatusTarefaEnum" :value="status">{{ status }}</option>
                        </select>
                    </div>
                </div>
        </template>
        <template v-slot:footer>
            <button class="btn btn-secondary" @click="fecharModal">Fechar</button>
            <button class="btn btn-primary" @click="alterarStatus">Alterar</button>
        </template>
    </ModalComponent>
</template>