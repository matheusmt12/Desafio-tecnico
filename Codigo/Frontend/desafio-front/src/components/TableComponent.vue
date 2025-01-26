<script setup>

const props = defineProps(['dados', 'titulos'])

// Função para dividir strings por letras maiúsculas
function splitByUppercase(str) {
    if (typeof str === 'string') {

        return str.split(/(?=[A-Z])|_/).join(' ').toUpperCase(); // Dividindo e juntando com espaço

    }

    return str.toUpperCase(); // Se não for string, retorna o valor como está
}

</script>

<template>
    <table class="table">
        <thead>
            <tr>
                <th v-for="t in titulos" class="text-captalize"><span>{{ splitByUppercase(t) }}</span></th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="dado in dados">
                <td v-for="chave in titulos " :key="chave">
                    {{ dado[chave] }}</td>
            </tr>
        </tbody>
    </table>
</template>
