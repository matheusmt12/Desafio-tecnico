import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import ProjetoView from '@/views/ProjetoView.vue'
import TarefaView from '@/views/TarefaView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/projeto',
      name: 'projeto',
      component: ProjetoView,
    },
    {
      path : '/tarefas/:id',
      name : 'tarefas',
      component : TarefaView
    }
  ],
})

export default router
