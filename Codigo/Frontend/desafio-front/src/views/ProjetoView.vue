<script setup>

//imports 
import { onMounted, ref } from 'vue';
import CardComponent from '@/components/CardComponent.vue';
import TableComponent from '@/components/TableComponent.vue';
import NavbarComponent from '@/components/NavbarComponent.vue';
import router from '@/router';
import ModalComponent from '@/components/ModalComponent.vue';
import InputComponent from '@/components/InputComponent.vue';
import StatusProjetoEnumInicial from '@/enums/StatusProjetoEnumInicial';
import StatusProjetoEnum from '@/enums/StatusProjetoEnum';
import AlertComponent from '@/components/AlertComponent.vue';
import PaginationComponent from '@/components/PaginationComponent.vue';
import RadioStatusComponent from '@/components/RadioStatusComponent.vue';
//variáveis 
const url = "http://localhost:8080/projeto";
const dadosProjeto = ref([]);
const nomePesquisa = ref("");
const tarefas = ref(true);
const finalizarProjeto = ref(true);
const dadosResponsavel = ref([]);
const projeto = ref(null);
const pageable = ref([]);
const numPage = ref(0);
const statusConsulta = ref("");

//variáveis para adicionar um novo projeto  
const nomeProjeto = ref("");
const decricaoProjeto = ref("");
const dataInicio = ref("");
const dataTermino = ref("");

//variáveis Alert

const sucesso = ref(false);
const menssagem = ref("");
const erro = ref(false);
const statusAlertSucesso = ref("");
const statusAlertErro = ref("");


//variáveis e funções modal
const modalVisivel = ref(false);
const modalVisivelStatus = ref(false);

const abrirModal = () => {
    modalVisivel.value = true
}

const fecharModal = () => {
    modalVisivel.value = false;
    modalVisivelStatus.value = false;

    sucesso.value = false;
    menssagem.value = "";
    erro.value = false;
    statusAlertSucesso.value = "";
    statusAlertErro.value = "";

}

function mudarStatusModal(obj) {
    modalVisivelStatus.value = true;
    projeto.value = obj;

}



//funções

function token() {
    return localStorage.getItem('token');
}

function buscarProjetos() {

    axios.get(url + "/index", {
        headers: {
            'Authorization': 'Bearer ' + token()
        },
        params: {
            page: numPage.value,
            nome: nomePesquisa.value,
            status: statusConsulta.value
        }
    }).then(response => {
        dadosProjeto.value = response.data.content;
        pageable.value = response.data;
    });

}

// buscar por nome
function pesquisarNome(params) {
    nomePesquisa.value = params;
    buscarProjetos();
}

function salvarProjeto() {
    let statusProjeto = document.getElementById('statusProjeto').value;
    let idResponsavel = document.getElementById('idResponsavel').value;

    let data = {
        nome: nomeProjeto.value,
        descricao: decricaoProjeto.value,
        data_inicio: dataInicio.value,
        data_termino: dataTermino.value,
        status: statusProjeto,
        id_responsavel: idResponsavel
    };
    axios.post(url, data, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {
        console.log(response.data.message);

        //resetar as variáveis do projeto
        nomeProjeto.value = "";
        decricaoProjeto.value = "";
        dataInicio.value = "";
        dataTermino.value = "";

        // menssagem alert
        menssagem.value = response.data.message[0];
        sucesso.value = true;
        erro.value = false;
        statusAlertSucesso.value = "Sucesso";
        buscarProjetos();


    }).catch(error => {
        menssagem.value = error;
        erro.value = true;
        sucesso.value = false;
        statusAlertErro.value = "Erro";
    })


}

// Alterar Status Projeto

function alterarStatus(idProjeto) {
    let status = document.getElementById("projetoStatus").value;

    axios.put(url + '/alterarStatusProjeto/' + idProjeto, {
        status: status
    }, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {
        sucesso.value = true;
        erro.value = false;
        menssagem.value = response.data;
        statusAlertSucesso.value = "Sucesso";
        buscarProjetos();

    }).catch(error => {
        erro.value = true;
        sucesso.value = false;
        menssagem.value = error.response.data;
        statusAlertErro.value = "Erro"
    })

}

function mudarPage(page) {
    numPage.value = page;
    buscarProjetos();
}


function consultaPorStatus(status) {
    statusConsulta.value = status;
    
    buscarProjetos();
}

//metodo para redirecionar para as tarefas do projeto
function tarefa(id) {
    router.push('/tarefas/' + id)

}


onMounted(() => {
    buscarProjetos();
    const carregarResponsavelProjeto = () => {
        let url = "http://localhost:8080/responsavelprojeto";
        axios.get(url, {
            headers: {
                'Authorization': 'Bearer ' + token()
            }
        }).then(response => {
            dadosResponsavel.value = response.data
        }).catch(error => {
            console.log(error);

        })
    }
    carregarResponsavelProjeto()
});




</script>
<template>
    <NavbarComponent @pesquisarNome="pesquisarNome" place="Nome Projeto"></NavbarComponent>
    <CardComponent titulo="Projetos">
        <template v-slot:conteudo>
            <div class="row" style="padding-bottom: 5px;">
                <div class="col text-start">
                    <button class="btn btn-primary " @click="abrirModal">Novo Projeto</button>
                </div>
                <div class="col">
                    <RadioStatusComponent :titulos="['Planejado','Em execução','Abortado','Finalizado']" @status-pesquisa="consultaPorStatus"></RadioStatusComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <TableComponent :dados="dadosProjeto"
                        :titulos="['nome', 'nome_responsavel', 'status', 'data_termino']"
                        :finalizarProjeto="finalizarProjeto" :tarefas="tarefas" @funcTarefas="tarefa"
                        @funcFinalizar="mudarStatusModal">
                    </TableComponent>
                </div>
            </div>


        </template>
        <template v-slot:footer>
            <PaginationComponent :dados-pagina="pageable" @mudar-pagina="mudarPage"></PaginationComponent>
        </template>
    </CardComponent>


    <ModalComponent :visivel="modalVisivel" titulo="Novo Projeto">
        <template v-slot:alert>
            <AlertComponent v-if="sucesso" :message="menssagem" classAlert="alert alert-success"
                :status="statusAlertSucesso"></AlertComponent>
            <AlertComponent v-if="erro" :message="menssagem" classAlert="alert alert-danger" :status="statusAlertErro">
            </AlertComponent>
        </template>
        <template v-slot:conteudo>
            <div class="row">
                <div class="col">
                    <InputComponent forId="idNome" label="Nome">
                        <input type="text" id="idNome" placeholder="Nome" class="form-control" required
                            v-model="nomeProjeto">
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent forId="idDescricao" label="Descrição">
                        <input type="text" id="idDescricao" placeholder="Descricao" class="form-control" required
                            v-model="decricaoProjeto">
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <InputComponent forId="idDataInicio" label="Data Inicio">
                        <input type="date" class="form-control" required v-model="dataInicio">
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent forId="idDataTermino" label="Data Termino">
                        <input type="date" class="form-control" required v-model="dataTermino">
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <select name="statusProjeto" id="statusProjeto" class="form-control">
                        <option v-for="i in StatusProjetoEnumInicial" :value="i">{{ i }}</option>
                    </select>
                </div>
                <div class="col">
                    <select name="idResponsavel" id="idResponsavel" class="form-control">
                        <option v-for="r in dadosResponsavel" :value="r.id">{{ r.nome }}</option>
                    </select>
                </div>
            </div>
        </template>
        <template v-slot:footer>
            <button class="btn btn-secondary" @click="fecharModal">Fechar</button>
            <button class="btn btn-primary" @click="salvarProjeto">Adicionar</button>
        </template>
    </ModalComponent>
    <ModalComponent :visivel="modalVisivelStatus" titulo="Alterar Status">
        <template v-slot:alert>
            <AlertComponent v-if="sucesso" :message="menssagem" classAlert="alert alert-success"
                :status="statusAlertSucesso"></AlertComponent>
            <AlertComponent v-if="erro" :message="menssagem" classAlert="alert alert-danger" :status="statusAlertErro">
            </AlertComponent>
        </template>
        <template v-slot:conteudo>
            <div class="row">
                <div class="col">
                    <InputComponent label="Nome">
                        <label class="form-control">{{ projeto.nome }}</label>
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent label="Descrição">
                        <label class="form-control">{{ projeto.descricao }}</label>
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <InputComponent label="Status Atual">
                        <label class="form-control">{{ projeto.status }}</label>
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent label="Responsavel">
                        <label class="form-control">{{ projeto.nome_responsavel }}</label>
                    </InputComponent>
                </div>
            </div>
            <h5>Alterar</h5>
            <div class="row">
                <div class="col">
                    <select name="projetoStatus" id="projetoStatus" class="form-control">
                        <option v-for="status in StatusProjetoEnum" :value="status">{{ status }}</option>
                    </select>
                </div>
            </div>
        </template>
        <template v-slot:footer>
            <button class="btn btn-secondary" @click="fecharModal">Fechar</button>
            <button class="btn btn-primary" @click="alterarStatus(projeto.id)">Alterar</button>
        </template>
    </ModalComponent>
</template>