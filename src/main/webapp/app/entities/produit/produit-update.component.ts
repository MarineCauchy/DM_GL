import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, decimal, minValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import CommandeService from '@/entities/commande/commande.service';
import { ICommande } from '@/shared/model/commande.model';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import RestaurantService from '@/entities/restaurant/restaurant.service';
import { IRestaurant } from '@/shared/model/restaurant.model';

import { IProduit, Produit } from '@/shared/model/produit.model';
import ProduitService from './produit.service';

const validations: any = {
  produit: {
    nom: {
      required,
    },
    prix: {
      required,
      decimal,
      min: minValue(0),
    },
  },
};

@Component({
  validations,
})
export default class ProduitUpdate extends Vue {
  @Inject('produitService') private produitService: () => ProduitService;
  @Inject('alertService') private alertService: () => AlertService;

  public produit: IProduit = new Produit();

  @Inject('commandeService') private commandeService: () => CommandeService;

  public commandes: ICommande[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('restaurantService') private restaurantService: () => RestaurantService;

  public restaurants: IRestaurant[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.produitId) {
        vm.retrieveProduit(to.params.produitId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.produit.id) {
      this.produitService()
        .update(this.produit)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.produit.updated', { param: param.id });
          return (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.produitService()
        .create(this.produit)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.produit.created', { param: param.id });
          (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveProduit(produitId): void {
    this.produitService()
      .find(produitId)
      .then(res => {
        this.produit = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.commandeService()
      .retrieve()
      .then(res => {
        this.commandes = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.restaurantService()
      .retrieve()
      .then(res => {
        this.restaurants = res.data;
      });
  }
}
