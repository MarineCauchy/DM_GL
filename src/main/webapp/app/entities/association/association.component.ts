import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAssociation } from '@/shared/model/association.model';

import AssociationService from './association.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Association extends Vue {
  @Inject('associationService') private associationService: () => AssociationService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public associations: IAssociation[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAssociations();
  }

  public clear(): void {
    this.retrieveAllAssociations();
  }

  public retrieveAllAssociations(): void {
    this.isFetching = true;
    this.associationService()
      .retrieve()
      .then(
        res => {
          this.associations = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IAssociation): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAssociation(): void {
    this.associationService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('coopcycleApp.association.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllAssociations();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
