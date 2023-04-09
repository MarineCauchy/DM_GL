import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ProduitService from '@/entities/produit/produit.service';
import { IProduit } from '@/shared/model/produit.model';

import CommandeService from '@/entities/commande/commande.service';
import { ICommande } from '@/shared/model/commande.model';

import { IClient, Client } from '@/shared/model/client.model';
import ClientService from './client.service';

const validations: any = {
  client: {
    idC: {
      required,
      numeric,
    },
    nomC: {
      required,
      maxLength: maxLength(20),
    },
    prenomC: {
      required,
      maxLength: maxLength(20),
    },
    adresseC: {
      required,
      maxLength: maxLength(100),
    },
    emailC: {
      required,
      maxLength: maxLength(50),
    },
    telephoneC: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class ClientUpdate extends Vue {
  @Inject('clientService') private clientService: () => ClientService;
  @Inject('alertService') private alertService: () => AlertService;

  public client: IClient = new Client();

  @Inject('produitService') private produitService: () => ProduitService;

  public produits: IProduit[] = [];

  @Inject('commandeService') private commandeService: () => CommandeService;

  public commandes: ICommande[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.clientId) {
        vm.retrieveClient(to.params.clientId);
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
    if (this.client.id) {
      this.clientService()
        .update(this.client)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.client.updated', { param: param.id });
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
      this.clientService()
        .create(this.client)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.client.created', { param: param.id });
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

  public retrieveClient(clientId): void {
    this.clientService()
      .find(clientId)
      .then(res => {
        this.client = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.produitService()
      .retrieve()
      .then(res => {
        this.produits = res.data;
      });
    this.commandeService()
      .retrieve()
      .then(res => {
        this.commandes = res.data;
      });
  }
}
