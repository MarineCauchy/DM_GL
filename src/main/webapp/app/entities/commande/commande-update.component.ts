import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, decimal, minValue } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import ProduitService from '@/entities/produit/produit.service';
import { IProduit } from '@/shared/model/produit.model';

import LivreurService from '@/entities/livreur/livreur.service';
import { ILivreur } from '@/shared/model/livreur.model';

import { ICommande, Commande } from '@/shared/model/commande.model';
import CommandeService from './commande.service';

const validations: any = {
  commande: {
    idCo: {
      required,
      numeric,
    },
    prixTotal: {
      required,
      decimal,
      min: minValue(0),
    },
    date: {
      required,
    },
    adresseLivraison: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class CommandeUpdate extends Vue {
  @Inject('commandeService') private commandeService: () => CommandeService;
  @Inject('alertService') private alertService: () => AlertService;

  public commande: ICommande = new Commande();

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('produitService') private produitService: () => ProduitService;

  public produits: IProduit[] = [];

  @Inject('livreurService') private livreurService: () => LivreurService;

  public livreurs: ILivreur[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.commandeId) {
        vm.retrieveCommande(to.params.commandeId);
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
    if (this.commande.id) {
      this.commandeService()
        .update(this.commande)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.commande.updated', { param: param.id });
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
      this.commandeService()
        .create(this.commande)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.commande.created', { param: param.id });
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.commande[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.commande[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.commande[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.commande[field] = null;
    }
  }

  public retrieveCommande(commandeId): void {
    this.commandeService()
      .find(commandeId)
      .then(res => {
        res.date = new Date(res.date);
        this.commande = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.produitService()
      .retrieve()
      .then(res => {
        this.produits = res.data;
      });
    this.livreurService()
      .retrieve()
      .then(res => {
        this.livreurs = res.data;
      });
  }
}
