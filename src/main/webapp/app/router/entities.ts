import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Client = () => import('@/entities/client/client.vue');
// prettier-ignore
const ClientUpdate = () => import('@/entities/client/client-update.vue');
// prettier-ignore
const ClientDetails = () => import('@/entities/client/client-details.vue');
// prettier-ignore
const Commande = () => import('@/entities/commande/commande.vue');
// prettier-ignore
const CommandeUpdate = () => import('@/entities/commande/commande-update.vue');
// prettier-ignore
const CommandeDetails = () => import('@/entities/commande/commande-details.vue');
// prettier-ignore
const Restaurant = () => import('@/entities/restaurant/restaurant.vue');
// prettier-ignore
const RestaurantUpdate = () => import('@/entities/restaurant/restaurant-update.vue');
// prettier-ignore
const RestaurantDetails = () => import('@/entities/restaurant/restaurant-details.vue');
// prettier-ignore
const Produit = () => import('@/entities/produit/produit.vue');
// prettier-ignore
const ProduitUpdate = () => import('@/entities/produit/produit-update.vue');
// prettier-ignore
const ProduitDetails = () => import('@/entities/produit/produit-details.vue');
// prettier-ignore
const Livreur = () => import('@/entities/livreur/livreur.vue');
// prettier-ignore
const LivreurUpdate = () => import('@/entities/livreur/livreur-update.vue');
// prettier-ignore
const LivreurDetails = () => import('@/entities/livreur/livreur-details.vue');
// prettier-ignore
const Association = () => import('@/entities/association/association.vue');
// prettier-ignore
const AssociationUpdate = () => import('@/entities/association/association-update.vue');
// prettier-ignore
const AssociationDetails = () => import('@/entities/association/association-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commande',
      name: 'Commande',
      component: Commande,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commande/new',
      name: 'CommandeCreate',
      component: CommandeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commande/:commandeId/edit',
      name: 'CommandeEdit',
      component: CommandeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commande/:commandeId/view',
      name: 'CommandeView',
      component: CommandeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant',
      name: 'Restaurant',
      component: Restaurant,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant/new',
      name: 'RestaurantCreate',
      component: RestaurantUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant/:restaurantId/edit',
      name: 'RestaurantEdit',
      component: RestaurantUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'restaurant/:restaurantId/view',
      name: 'RestaurantView',
      component: RestaurantDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit',
      name: 'Produit',
      component: Produit,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/new',
      name: 'ProduitCreate',
      component: ProduitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/:produitId/edit',
      name: 'ProduitEdit',
      component: ProduitUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'produit/:produitId/view',
      name: 'ProduitView',
      component: ProduitDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'livreur',
      name: 'Livreur',
      component: Livreur,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'livreur/new',
      name: 'LivreurCreate',
      component: LivreurUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'livreur/:livreurId/edit',
      name: 'LivreurEdit',
      component: LivreurUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'livreur/:livreurId/view',
      name: 'LivreurView',
      component: LivreurDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association',
      name: 'Association',
      component: Association,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association/new',
      name: 'AssociationCreate',
      component: AssociationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association/:associationId/edit',
      name: 'AssociationEdit',
      component: AssociationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'association/:associationId/view',
      name: 'AssociationView',
      component: AssociationDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
