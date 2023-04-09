import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import ClientService from './client/client.service';
import CommandeService from './commande/commande.service';
import RestaurantService from './restaurant/restaurant.service';
import ProduitService from './produit/produit.service';
import LivreurService from './livreur/livreur.service';
import AssociationService from './association/association.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('commandeService') private commandeService = () => new CommandeService();
  @Provide('restaurantService') private restaurantService = () => new RestaurantService();
  @Provide('produitService') private produitService = () => new ProduitService();
  @Provide('livreurService') private livreurService = () => new LivreurService();
  @Provide('associationService') private associationService = () => new AssociationService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
