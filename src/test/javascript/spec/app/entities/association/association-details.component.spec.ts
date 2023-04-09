/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AssociationDetailComponent from '@/entities/association/association-details.vue';
import AssociationClass from '@/entities/association/association-details.component';
import AssociationService from '@/entities/association/association.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Association Management Detail Component', () => {
    let wrapper: Wrapper<AssociationClass>;
    let comp: AssociationClass;
    let associationServiceStub: SinonStubbedInstance<AssociationService>;

    beforeEach(() => {
      associationServiceStub = sinon.createStubInstance<AssociationService>(AssociationService);

      wrapper = shallowMount<AssociationClass>(AssociationDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { associationService: () => associationServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAssociation = { id: 123 };
        associationServiceStub.find.resolves(foundAssociation);

        // WHEN
        comp.retrieveAssociation(123);
        await comp.$nextTick();

        // THEN
        expect(comp.association).toBe(foundAssociation);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAssociation = { id: 123 };
        associationServiceStub.find.resolves(foundAssociation);

        // WHEN
        comp.beforeRouteEnter({ params: { associationId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.association).toBe(foundAssociation);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
