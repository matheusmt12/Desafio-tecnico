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
import StatusTarefaInicioEnum from '@/enums/StatusTarefaInicioEnum';
import AlertComponent from '@/components/AlertComponent.vue';


//variáveis
const url = "http://localhost:8080/tarefa";
const idProjeto = router.currentRoute.value.params.id;
const dadosTarefas = ref([]);
const dadosResponsaveis = ref([]);
const statusAlterar = ref(true);
const tarefa = ref(null);

//variáveis Alert

const sucesso = ref(false);
const menssagem = ref("");
const erro = ref(false);
const statusAlertSucesso = ref("");
const statusAlertErro = ref("");


// variáveis para adicionar uma nova tarefa

const titulo = ref("");
const descricao = ref("");
const prazo = ref(0);

//Modal funções e variáveis 
const visivelModal = ref(false);
const visivelModalStatus = ref()
function abrirModal() {
    visivelModal.value = true;
}

function fecharModal() {
    visivelModal.value = false;
    visivelModalStatus.value = false;
    sucesso.value = false;
    menssagem.value ="";
    erro.value = false;
    statusAlertSucesso.value = "";
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

function salvarNovaTarefa() {
    let idResponsavel = document.getElementById('idResponsavel').value;
    let status = document.getElementById('tarefaStatus').value;

    let data = {
        titulo: titulo.value,
        descricao: descricao.value,
        prazo: prazo.value,
        status: status,
        id_responsavel: idResponsavel,
        id_projeto: idProjeto

    }

    axios.post(url, data, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {

        sucesso.value = true;
        menssagem.value = response.data;
        erro.value = false;
        statusAlertSucesso.value = "Sucesso";

        buscarTarefas();


    }).catch(error => {

        sucesso.value = false;
        menssagem.value = error.response.data;
        erro.value = true;
        statusAlertErro.value = "Erro";
    })
}

function alterarStatus() {
    let status = document.getElementById('statusTarefa').value;

    axios.put(url + '/alterar/' + tarefa.value.id, {
        status: status
    }, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }
    ).then(response => {
        console.log(response.data);
        sucesso.value = true;
        menssagem.value = response.data.message[0];
        erro.value = false;
        statusAlertSucesso.value = "Sucesso";
        buscarTarefas();
    }).catch(error => {

        sucesso.value = false;
        menssagem.value = error.response.message;
        erro.value = true;
        statusAlertErro.value = "Erro";
    })

}



onMounted(() => {
    buscarTarefas();
    const buscarResponsaveis = () => {
        let url = 'http://localhost:8080/responsaveltarefa';
        axios.get(url, {
            headers: {
                'Authorization': 'Bearer ' + token()
            }
        }).then(response => {
            dadosResponsaveis.value = response.data;

        })
    };
    buscarResponsaveis();
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
            <button class="btn btn-primary" @click="abrirModal">Adicionar</button>
        </template>
    </CardComponent>

    <ModalComponent titulo="Nova Tarefa" :visivel="visivelModal">
        <template v-slot:alert>
            <AlertComponent v-if="sucesso" :message="menssagem" classAlert="alert alert-success"
                :status="statusAlertSucesso"></AlertComponent>
            <AlertComponent v-if="erro" :message="menssagem" classAlert="alert alert-danger" :status="statusAlertErro">
            </AlertComponent>
        </template>
        <template v-slot:conteudo>
            <div class="row">
                <div class="col">
                    <InputComponent label="Titulo" for-id="tituloTarefa">
                        <input type="text" class="form-control" required v-model="titulo">
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent label="Descrição" for-id="descricaoTarefa">
                        <input type="text" class="form-control" required v-model="descricao">
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <InputComponent label="Prazo" for-id="prazoTarefa">
                        <input type="number" min="1" required v-model="prazo" class="form-control">
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent label="responsavelTarefa" for-id="responsavelTarefa">
                        <select name="idResponsavel" id="idResponsavel" class="form-control">
                            <option v-for="i in dadosResponsaveis" :value="i.id">{{ i.nome }}</option>
                        </select>
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <InputComponent label="Status">
                        <select name="tarefaStatus" id="tarefaStatus" class="form-control">
                            <option v-for="i in StatusTarefaInicioEnum" :value="i">{{ i }}</option>
                        </select>
                    </InputComponent>
                </div>
            </div>
        </template>
        <template v-slot:footer>
            <button class="btn btn-secondary" @click="fecharModal">Fechar</button>
            <button class="btn btn-primary" @click="salvarNovaTarefa">Salvar</button>
        </template>
    </ModalComponent>

    <ModalComponent :visivel="visivelModalStatus" titulo="Alterar Status">
        <template v-slot:alert>
            <AlertComponent v-if="sucesso" :message="menssagem" classAlert="alert alert-success"
                :status="statusAlertSucesso"></AlertComponent>
            <AlertComponent v-if="erro" :message="menssagem" classAlert="alert alert-danger" :status="statusAlertErro">
            </AlertComponent>
        </template>
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
                    <select name="statusTarefa" id="statusTarefa" class="form-control">
                        <option v-for="status in StatusTarefaEnum" :value="status">{{ status }}</option>
                    </select>
                </div>
            </div>
        </template>
        <template v-slot:footer>
            <button class="btn btn-secondary" @click="fecharModal">Fechar</button>
            <button class="btn btn-primary" @click="alterarStatus()">Alterar</button>
        </template>
    </ModalComponent>
</template>