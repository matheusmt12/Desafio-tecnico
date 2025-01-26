<script setup>

//imports 
import { onMounted, ref } from 'vue';
import CardComponent from '@/components/CardComponent.vue';
import TableComponent from '@/components/TableComponent.vue';


//variáveis 
const url = "http://localhost:8080/projeto";
const dadosProjeto = ref([]);

const buscarProjetos = () =>{

    let token = localStorage.getItem('token');

    console.log(token);
    
    axios.get(url, {
        headers :{
            'Authorization' : 'Bearer ' +token
        }
    }).then(response => {
        dadosProjeto.value = response.data   
        console.log(dadosProjeto.value);
        
    });

}

onMounted(() =>{
    buscarProjetos();
});

</script>
<template>
    <h1>Esta na view</h1>
    <CardComponent titulo="Projetos">
        <template v-slot:conteudo>
           <TableComponent :dados="dadosProjeto" :titulos="['id','nome']"></TableComponent>
        </template>
        <template v-slot:footer>
            <button class="btn btn-primary ">teste</button>
        </template>
    </CardComponent>
</template>