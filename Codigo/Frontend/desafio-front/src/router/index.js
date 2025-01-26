import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import ProjetoView from '@/views/ProjetoView.vue'
import TarefaView from '@/views/TarefaView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    }, {
      path: '/login',
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
