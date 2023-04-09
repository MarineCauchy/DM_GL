<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.livreur.home.createOrEditLabel"
          data-cy="LivreurCreateUpdateHeading"
          v-text="$t('coopcycleApp.livreur.home.createOrEditLabel')"
        >
          Create or edit a Livreur
        </h2>
        <div>
          <div class="form-group" v-if="livreur.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="livreur.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.livreur.idL')" for="livreur-idL">Id L</label>
            <input
              type="number"
              class="form-control"
              name="idL"
              id="livreur-idL"
              data-cy="idL"
              :class="{ valid: !$v.livreur.idL.$invalid, invalid: $v.livreur.idL.$invalid }"
              v-model.number="$v.livreur.idL.$model"
              required
            />
            <div v-if="$v.livreur.idL.$anyDirty && $v.livreur.idL.$invalid">
              <small class="form-text text-danger" v-if="!$v.livreur.idL.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.livreur.idL.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.livreur.nomL')" for="livreur-nomL">Nom L</label>
            <input
              type="text"
              class="form-control"
              name="nomL"
              id="livreur-nomL"
              data-cy="nomL"
              :class="{ valid: !$v.livreur.nomL.$invalid, invalid: $v.livreur.nomL.$invalid }"
              v-model="$v.livreur.nomL.$model"
              required
            />
            <div v-if="$v.livreur.nomL.$anyDirty && $v.livreur.nomL.$invalid">
              <small class="form-text text-danger" v-if="!$v.livreur.nomL.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.livreur.nomL.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.livreur.nomL.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Nom L' })"
              >
                This field should follow pattern for "Nom L".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.livreur.prenomL')" for="livreur-prenomL">Prenom L</label>
            <input
              type="text"
              class="form-control"
              name="prenomL"
              id="livreur-prenomL"
              data-cy="prenomL"
              :class="{ valid: !$v.livreur.prenomL.$invalid, invalid: $v.livreur.prenomL.$invalid }"
              v-model="$v.livreur.prenomL.$model"
              required
            />
            <div v-if="$v.livreur.prenomL.$anyDirty && $v.livreur.prenomL.$invalid">
              <small class="form-text text-danger" v-if="!$v.livreur.prenomL.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.livreur.prenomL.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.livreur.prenomL.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Prenom L' })"
              >
                This field should follow pattern for "Prenom L".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.livreur.telephoneL')" for="livreur-telephoneL">Telephone L</label>
            <input
              type="text"
              class="form-control"
              name="telephoneL"
              id="livreur-telephoneL"
              data-cy="telephoneL"
              :class="{ valid: !$v.livreur.telephoneL.$invalid, invalid: $v.livreur.telephoneL.$invalid }"
              v-model="$v.livreur.telephoneL.$model"
              required
            />
            <div v-if="$v.livreur.telephoneL.$anyDirty && $v.livreur.telephoneL.$invalid">
              <small class="form-text text-danger" v-if="!$v.livreur.telephoneL.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.livreur.telephoneL.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Telephone L' })"
              >
                This field should follow pattern for "Telephone L".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.livreur.association')" for="livreur-association">Association</label>
            <select class="form-control" id="livreur-association" data-cy="association" name="association" v-model="livreur.association">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  livreur.association && associationOption.id === livreur.association.id ? livreur.association : associationOption
                "
                v-for="associationOption in associations"
                :key="associationOption.id"
              >
                {{ associationOption.id }}
              </option>
            </select>
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
            :disabled="$v.livreur.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./livreur-update.component.ts"></script>
