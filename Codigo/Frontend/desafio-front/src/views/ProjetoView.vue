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
//variáveis 
const url = "http://localhost:8080/projeto";
const dadosProjeto = ref([]);
const nomePesquisa = ref("");
const tarefas = ref(true);
const dadosResponsavel = ref([]);

//variáveis para adicionar um novo projeto  
const nomeProjeto = ref("");
const decricaoProjeto = ref("");
const dataInicio = ref("");
const dataTermino = ref("");

//variáveis e funções modal
const modalVisivel = ref(false);

const abrirModal = () => {
    modalVisivel.value = true
}

const fecharModal = () => {
    modalVisivel.value = false
}


//funções

function token() {
    return localStorage.getItem('token');
}

function buscarProjetos() {

    axios.get(url, {
        headers: {
            'Authorization': 'Bearer ' + token()
        }
    }).then(response => {
        dadosProjeto.value = response.data
    });

}

function pesquisarNome(params) {
    nomePesquisa.value = params;
    console.log(nomePesquisa.value);

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
        nomeProjeto.value = "";
        decricaoProjeto.value = "";
        dataInicio.value= "";
        dataTermino.value = ""
        buscarProjetos();

    })


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
    <NavbarComponent @pesquisarNome="pesquisarNome"></NavbarComponent>
    <CardComponent titulo="Projetos">
        <template v-slot:conteudo>
            <TableComponent :dados="dadosProjeto" :titulos="['nome', 'nome_responsavel', 'status', 'data_termino']"
                :tarefas="tarefas" @funcTarefas="tarefa">
            </TableComponent>
        </template>
        <template v-slot:footer>
            <button class="btn btn-primary " @click="abrirModal">Novo Projeto</button>
        </template>
    </CardComponent>


    <ModalComponent :visivel="modalVisivel" titulo="Novo Projeto">
        <template v-slot:conteudo>
            <div class="row">
                <div class="col">
                    <InputComponent forId="idNome" label="Nome">
                        <input type="text" id="idNome" placeholder="Nome" class="form-control" required
                            v-model="nomeProjeto">
                    </InputComponent>
                </div>
                <div class="col">
                    <InputComponent forId="idDescricao" label="Nome">
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
</template>