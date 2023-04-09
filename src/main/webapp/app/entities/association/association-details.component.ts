import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAssociation } from '@/shared/model/association.model';
import AssociationService from './association.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class AssociationDetails extends Vue {
  @Inject('associationService') private associationService: () => AssociationService;
  @Inject('alertService') private alertService: () => AlertService;

  public association: IAssociation = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.associationId) {
        vm.retrieveAssociation(to.params.associationId);
      }
    });
  }

  public retrieveAssociation(associationId) {
    this.associationService()
      .find(associationId)
      .then(res => {
        this.association = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
