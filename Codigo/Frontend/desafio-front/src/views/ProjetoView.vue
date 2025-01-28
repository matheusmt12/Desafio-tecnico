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
import moment from 'moment';

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

//Variaveis para erro de input

const errorMessage = ref({
    nome: '',
    data_inicio: '',
    data_termino: '',
    descricao: ''
})

//variáveis para adicionar um novo projeto  
const nomeProjeto = ref("");
const decricaoProjeto = ref("");
const dataInicio = ref("");
const dataTermino = ref("");

//variáveis Alert
const menssagem = ref("");

const sucesso = ref(false);
const statusAlertSucesso = ref("");

const erro = ref(false);
const statusAlertErro = ref("");

const alerta = ref(false);
const statusAlertInfo = ref("");

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

    nomeProjeto.value = ''
    decricaoProjeto.value = '';
    dataInicio.value = '';
    dataTermino.value = '';
}

function mudarStatusModal(obj) {
    modalVisivelStatus.value = true;
    projeto.value = obj;

}



//funções token

function token() {
    return localStorage.getItem('token');
}

function verificarToken(menssagem) {
    if ('Token inválido!' === menssagem) {
        router.push({ path: '/login', query: { menssagem: 'Sessão expirada' } });
    }
}


//funçoes 
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
    }).catch(error => {

        if (error.response.status === 401) {
            verificarToken(error.response.data.message[0]);
        }
    });

}

// buscar por nome
function pesquisarNome(params) {
    nomePesquisa.value = params;
    buscarProjetos();
}

//SALVAR 
function salvarProjeto() {
    let statusProjeto = document.getElementById('statusProjeto').value;
    let idResponsavel = document.getElementById('idResponsavel').value;

    //limpando menssagens de erro

    errorMessage.value.data_inicio = '';
    errorMessage.value.data_termino = '';
    errorMessage.value.nome = '';
    errorMessage.value.descricao = '';
    let data = {
        nome: nomeProjeto.value,
        descricao: decricaoProjeto.value,
        data_inicio: dataInicio.value,
        data_termino: dataTermino.value,
        status: statusProjeto,
        id_responsavel: idResponsavel
    };

    console.log(data);

    axios.post(url, data, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {

        //resetar as variáveis do projeto
        nomeProjeto.value = "";
        decricaoProjeto.value = "";
        dataInicio.value = "";
        dataTermino.value = "";

        // menssagem alert
        menssagem.value = response.data;
        console.log(menssagem.value);

        sucesso.value = true;
        erro.value = false;
        statusAlertSucesso.value = "Sucesso";
        buscarProjetos();


    }).catch(error => {
        errorMessage.value.nome = error.response.data.nome;
        errorMessage.value.data_inicio = error.response.data.data_inicio;
        errorMessage.value.data_termino = error.response.data.data_termino;
        errorMessage.value.descricao = error.response.data.descricao
        erro.value = true;
        sucesso.value = false;
        statusAlertErro.value = "Erro:";

        if (errorMessage.value.data_inicio || errorMessage.value.data_termino ||
            errorMessage.value.descricao || errorMessage.value.nome) {
            menssagem.value = "Preencha os campos necessários";

            return;
        }

        menssagem.value = error.response.data;

        if (error.response.status === 401) {
            verificarToken(error.response.data.message[0]);
        }

    })


}

// Alterar Status Projeto

function alterarStatus(idProjeto) {
    let status = document.getElementById("projetoStatus").value;

    menssagem.value = '';
    alerta.value = false;
    statusAlertInfo.value = '';

    if (status === projeto.value.status) {
        alerta.value = true;
        menssagem.value = 'O status é o mesmo que o anterior!';
        statusAlertInfo.value = 'Info:'

        return;
    }


    axios.put(url + '/alterarStatusProjeto/' + idProjeto, {
        status: status
    }, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {
        sucesso.value = true;
        erro.value = false;
        alerta.value = false;
        menssagem.value = response.data;
        statusAlertSucesso.value = "Sucesso";
        projeto.value.status = status;
        mudarStatusModal(projeto.value);
        buscarProjetos();

    }).catch(error => {
        erro.value = true;
        sucesso.value = false;
        menssagem.value = error.response.data;
        statusAlertErro.value = "Erro";
        alerta.value = false;
        if (error.response.status === 401) {
            verificarToken(error.response.data.message[0]);
        }
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

//funçõa para formatar a data



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

            if (error.response.status === 401) {
                verificarToken(error.response.data.message[0]);
            }
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
                <div class="col text-end">
                    <RadioStatusComponent :titulos="['Planejado', 'Em execução', 'Abortado', 'Finalizado']"
                        @status-pesquisa="consultaPorStatus"></RadioStatusComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <span v-if="dadosProjeto.length === 0 && statusConsulta != ''">
                        <p>Não há nenhum projeto {{ statusConsulta }} </p>
                    </span>
                    <span v-else-if="dadosProjeto.length === 0">
                        <p v-if="nomePesquisa != ''"> Não há nenhum projeto {{ nomePesquisa }}</p>
                        <p v-else> Não há nenhum projeto </p>
                    </span>
                    <span v-else>
                        <TableComponent :dados="dadosProjeto"
                            :titulos="['nome', 'nome_responsavel', 'status', 'data_termino']"
                            :finalizarProjeto="finalizarProjeto" :tarefas="tarefas" @funcTarefas="tarefa"
                            @funcFinalizar="mudarStatusModal">
                        </TableComponent>
                    </span>
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
                        <span v-if="errorMessage.nome != ''" style="color: red;">{{ errorMessage.nome }}</span>
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent forId="idDescricao" label="Descrição">
                        <input type="text" id="idDescricao" placeholder="Descricao" class="form-control" required
                            v-model="decricaoProjeto">
                        <span v-if="errorMessage.descricao != ''" style="color: red;">{{ errorMessage.descricao
                            }}</span>
                    </InputComponent>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <InputComponent forId="idDataInicio" label="Data Inicio">
                        <input type="date" class="form-control" required v-model="dataInicio">
                        <span v-if="errorMessage.data_inicio != ''" style="color: red;">{{ errorMessage.data_inicio
                            }}</span>
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent forId="idDataTermino" label="Data Termino">
                        <input type="date" class="form-control" required v-model="dataTermino">
                        <span v-if="errorMessage.data_termino != ''" style="color: red;">{{
                            errorMessage.data_termino }}</span>

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
            <AlertComponent v-if="alerta" :message="menssagem" classAlert="alert alert-info" :status="statusAlertInfo">
            </AlertComponent>
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
                    <InputComponent label="Data Inicio">
                        <label class="form-control">{{moment(projeto.data_inicio ).format('DD/MM/YYYY') }}</label>
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent label="Data Término">
                        <label class="form-control">{{ moment(projeto.data_termino ).format('DD/MM/YYYY') }}</label>
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