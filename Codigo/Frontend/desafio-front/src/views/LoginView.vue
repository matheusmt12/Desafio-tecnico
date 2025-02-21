<script setup>
import CardComponent from '@/components/CardComponent.vue';
import InputComponent from '@/components/InputComponent.vue';
import router from '@/router';
import { onMounted, ref } from 'vue';

const url = "http://localhost:8080/usuario/login";

const usuario = ref("");
const senha = ref("");

const errorSenha = ref(false);
const errorMenssagem = ref("")
const errorUsuario = ref(false);

const menssagemToken = router.currentRoute.value.query.menssagem;
function login() {
    const data = { usuario: usuario.value, senha: senha.value }
    errorMenssagem.value = ''
    errorSenha.value = false;
    errorUsuario.value = false;

    axios.post(url, data).then(response => {
        let token = response.data.token;
        console.log("esta aqui");

        if (token) {
            
            localStorage.setItem('token', token);
        }
        router.push('/projeto')
    }).catch(error => {

        if (error.response.data === 'Senha inválida') {
            errorSenha.value = true;
            errorMenssagem.value = 'Senha inválida !';

        }
        if (error.response.data === 'Usuario não encontrado') {
            errorUsuario.value = true;
            errorMenssagem.value = 'Usuario não encontrado!';
        }
    })
}

onMounted(() => {
    const tokenExpirado = () => {
        if (menssagemToken) {
            alert("Seção expirada");
        }
    };
    tokenExpirado();
})
</script>

<template>
    <div class="login-container">
        <CardComponent titulo="Login">
            <template v-slot:conteudo>
                <InputComponent label="Usuario" for-id="idUsuario">
                    <input type="text" v-model="usuario" class="form-control">
                    <span v-if="errorUsuario" style="color: red;">{{ errorMenssagem }}</span>
                </InputComponent>
                <InputComponent label="Senha" for-id="idSenha">
                    <input type="password" v-model="senha" class="form-control">
                    <span v-if="errorSenha" style="color: red;">{{ errorMenssagem }}</span>
                </InputComponent>
            </template>
            <template v-slot:footer>
                <button @click="login" class="btn btn-primary">Entrar</button>
            </template>
        </CardComponent>
    </div>
</template>

<style scoped>
.login-container {
    display: flex;
    justify-content: center;

    padding-top: 10px;
}
</style>
