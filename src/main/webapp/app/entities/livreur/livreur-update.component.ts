import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import CommandeService from '@/entities/commande/commande.service';
import { ICommande } from '@/shared/model/commande.model';

import AssociationService from '@/entities/association/association.service';
import { IAssociation } from '@/shared/model/association.model';

import { ILivreur, Livreur } from '@/shared/model/livreur.model';
import LivreurService from './livreur.service';

const validations: any = {
  livreur: {
    idL: {
      required,
      numeric,
    },
    nomL: {
      required,
      maxLength: maxLength(50),
    },
    prenomL: {
      required,
      maxLength: maxLength(50),
    },
    telephoneL: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class LivreurUpdate extends Vue {
  @Inject('livreurService') private livreurService: () => LivreurService;
  @Inject('alertService') private alertService: () => AlertService;

  public livreur: ILivreur = new Livreur();

  @Inject('commandeService') private commandeService: () => CommandeService;

  public commandes: ICommande[] = [];

  @Inject('associationService') private associationService: () => AssociationService;

  public associations: IAssociation[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.livreurId) {
        vm.retrieveLivreur(to.params.livreurId);
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
    if (this.livreur.id) {
      this.livreurService()
        .update(this.livreur)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.livreur.updated', { param: param.id });
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
      this.livreurService()
        .create(this.livreur)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('coopcycleApp.livreur.created', { param: param.id });
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

  public retrieveLivreur(livreurId): void {
    this.livreurService()
      .find(livreurId)
      .then(res => {
        this.livreur = res;
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
    this.associationService()
      .retrieve()
      .then(res => {
        this.associations = res.data;
      });
  }
}
