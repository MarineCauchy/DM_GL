<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.client.home.createOrEditLabel"
          data-cy="ClientCreateUpdateHeading"
          v-text="$t('coopcycleApp.client.home.createOrEditLabel')"
        >
          Create or edit a Client
        </h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.client.idC')" for="client-idC">Id C</label>
            <input
              type="number"
              class="form-control"
              name="idC"
              id="client-idC"
              data-cy="idC"
              :class="{ valid: !$v.client.idC.$invalid, invalid: $v.client.idC.$invalid }"
              v-model.number="$v.client.idC.$model"
              required
            />
            <div v-if="$v.client.idC.$anyDirty && $v.client.idC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.idC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.client.idC.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.client.nomC')" for="client-nomC">Nom C</label>
            <input
              type="text"
              class="form-control"
              name="nomC"
              id="client-nomC"
              data-cy="nomC"
              :class="{ valid: !$v.client.nomC.$invalid, invalid: $v.client.nomC.$invalid }"
              v-model="$v.client.nomC.$model"
              required
            />
            <div v-if="$v.client.nomC.$anyDirty && $v.client.nomC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.nomC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.client.nomC.maxLength" v-text="$t('entity.validation.maxlength', { max: 20 })">
                This field cannot be longer than 20 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.nomC.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Nom C' })"
              >
                This field should follow pattern for "Nom C".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.client.prenomC')" for="client-prenomC">Prenom C</label>
            <input
              type="text"
              class="form-control"
              name="prenomC"
              id="client-prenomC"
              data-cy="prenomC"
              :class="{ valid: !$v.client.prenomC.$invalid, invalid: $v.client.prenomC.$invalid }"
              v-model="$v.client.prenomC.$model"
              required
            />
            <div v-if="$v.client.prenomC.$anyDirty && $v.client.prenomC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.prenomC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.prenomC.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 20 })"
              >
                This field cannot be longer than 20 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.prenomC.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Prenom C' })"
              >
                This field should follow pattern for "Prenom C".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.client.adresseC')" for="client-adresseC">Adresse C</label>
            <input
              type="text"
              class="form-control"
              name="adresseC"
              id="client-adresseC"
              data-cy="adresseC"
              :class="{ valid: !$v.client.adresseC.$invalid, invalid: $v.client.adresseC.$invalid }"
              v-model="$v.client.adresseC.$model"
              required
            />
            <div v-if="$v.client.adresseC.$anyDirty && $v.client.adresseC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.adresseC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.adresseC.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.client.emailC')" for="client-emailC">Email C</label>
            <input
              type="text"
              class="form-control"
              name="emailC"
              id="client-emailC"
              data-cy="emailC"
              :class="{ valid: !$v.client.emailC.$invalid, invalid: $v.client.emailC.$invalid }"
              v-model="$v.client.emailC.$model"
              required
            />
            <div v-if="$v.client.emailC.$anyDirty && $v.client.emailC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.emailC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.emailC.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.emailC.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Email C' })"
              >
                This field should follow pattern for "Email C".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.client.telephoneC')" for="client-telephoneC">Telephone C</label>
            <input
              type="text"
              class="form-control"
              name="telephoneC"
              id="client-telephoneC"
              data-cy="telephoneC"
              :class="{ valid: !$v.client.telephoneC.$invalid, invalid: $v.client.telephoneC.$invalid }"
              v-model="$v.client.telephoneC.$model"
              required
            />
            <div v-if="$v.client.telephoneC.$anyDirty && $v.client.telephoneC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.telephoneC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.telephoneC.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Telephone C' })"
              >
                This field should follow pattern for "Telephone C".
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.client.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./client-update.component.ts"></script>
