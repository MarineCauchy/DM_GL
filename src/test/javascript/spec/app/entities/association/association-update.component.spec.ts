/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import AssociationUpdateComponent from '@/entities/association/association-update.vue';
import AssociationClass from '@/entities/association/association-update.component';
import AssociationService from '@/entities/association/association.service';

import LivreurService from '@/entities/livreur/livreur.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Association Management Update Component', () => {
    let wrapper: Wrapper<AssociationClass>;
    let comp: AssociationClass;
    let associationServiceStub: SinonStubbedInstance<AssociationService>;

    beforeEach(() => {
      associationServiceStub = sinon.createStubInstance<AssociationService>(AssociationService);

      wrapper = shallowMount<AssociationClass>(AssociationUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          associationService: () => associationServiceStub,
          alertService: () => new AlertService(),

          livreurService: () =>
            sinon.createStubInstance<LivreurService>(LivreurService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.association = entity;
        associationServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(associationServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.association = entity;
        associationServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(associationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAssociation = { id: 123 };
        associationServiceStub.find.resolves(foundAssociation);
        associationServiceStub.retrieve.resolves([foundAssociation]);

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
