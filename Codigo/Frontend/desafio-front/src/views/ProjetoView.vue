<script setup>

//imports 
import { onMounted, ref } from 'vue';
import CardComponent from '@/components/CardComponent.vue';
import TableComponent from '@/components/TableComponent.vue';
import NavbarComponent from '@/components/NavbarComponent.vue';
import router from '@/router';
import ModalComponent from '@/components/ModalComponent.vue';


//variáveis 
const url = "http://localhost:8080/projeto";
const dadosProjeto = ref([]);
const nomePesquisa = ref("");
const tarefas = ref(true);

//variáveis e funções modal
const modalVisivel = ref(false);

const abrirModal = () =>{
    modalVisivel.value = true
}


//funções
function buscarProjetos() {

    let token = localStorage.getItem('token');

    console.log(token);

    axios.get(url, {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    }).then(response => {
        dadosProjeto.value = response.data
        console.log(dadosProjeto.value);

    });

}

function pesquisarNome(params) {
    nomePesquisa.value = params;
    console.log(nomePesquisa.value);

}

//metodo para redirecionar para as tarefas do projeto
function tarefa(id) {
    router.push('/tarefas/'+id)
    
}


onMounted(() => {
    buscarProjetos();
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
        <template v-slot:footer>
            <button class="btn btn-secondary">Fechar</button>
            <button class="btn btn-primary">Adicionar</button>
        </template>
    </ModalComponent>
</template>