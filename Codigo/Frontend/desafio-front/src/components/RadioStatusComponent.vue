<script setup>
const props = defineProps(['titulos']);
const emit = defineEmits(['statusPesquisa']);

function status(titulo) {
    emit('statusPesquisa', titulo);
}
</script>

<template>
    <div class="row">
        <div class="col" v-for="titulo in titulos">
            <div class="form-check">
                <input v-if="titulo == 'Todos'" class="form-check-input" type="radio" name="exampleRadios" :id="titulo"
                checked  :value="''" @click="status('')">
                <input v-else class="form-check-input" type="radio" name="exampleRadios" :id="titulo" :value="titulo"
                    @click="status(titulo)">
                <label class="form-check-label" for="exampleRadios1">
                    {{ titulo }}
                </label>
            </div>
        </div>
    </div>
</template>