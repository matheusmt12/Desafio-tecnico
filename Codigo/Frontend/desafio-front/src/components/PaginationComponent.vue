<script setup>
const props = defineProps(['dadosPagina']);
const emit = defineEmits(['mudarPagina'])


function mudarPagina(page) {
    emit('mudarPagina', page)

}
</script>

<template>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"  :class="{disabled : dadosPagina.number === 0}">
                <a class="page-link" href="#" @click="mudarPagina(dadosPagina.number - 1)">Anterior</a>
            </li>
            <li v-for="page in dadosPagina.totalPages" :key="page" class="page-item">
                <a href="#" class="page-link" @click="mudarPagina(page - 1)">{{ page }}</a>
            </li>
            <li class="page-item" :class="{disabled : dadosPagina.number === dadosPagina.totalPages -1 || dadosPagina.totalPages === 0}" >
                <a class="page-link" href="#" @click="mudarPagina(dadosPagina.number + 1)">Próximo</a>
            </li>
        </ul>
    </nav>
</template>