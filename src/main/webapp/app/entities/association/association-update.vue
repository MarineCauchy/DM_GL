<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.association.home.createOrEditLabel"
          data-cy="AssociationCreateUpdateHeading"
          v-text="$t('coopcycleApp.association.home.createOrEditLabel')"
        >
          Create or edit a Association
        </h2>
        <div>
          <div class="form-group" v-if="association.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="association.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.association.idA')" for="association-idA">Id A</label>
            <input
              type="number"
              class="form-control"
              name="idA"
              id="association-idA"
              data-cy="idA"
              :class="{ valid: !$v.association.idA.$invalid, invalid: $v.association.idA.$invalid }"
              v-model.number="$v.association.idA.$model"
              required
            />
            <div v-if="$v.association.idA.$anyDirty && $v.association.idA.$invalid">
              <small class="form-text text-danger" v-if="!$v.association.idA.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.association.idA.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.association.nomA')" for="association-nomA">Nom A</label>
            <input
              type="text"
              class="form-control"
              name="nomA"
              id="association-nomA"
              data-cy="nomA"
              :class="{ valid: !$v.association.nomA.$invalid, invalid: $v.association.nomA.$invalid }"
              v-model="$v.association.nomA.$model"
              required
            />
            <div v-if="$v.association.nomA.$anyDirty && $v.association.nomA.$invalid">
              <small class="form-text text-danger" v-if="!$v.association.nomA.required" v-text="$t('entity.validation.required')">
                This field is required.
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
            :disabled="$v.association.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./association-update.component.ts"></script>
