<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.restaurant.home.createOrEditLabel"
          data-cy="RestaurantCreateUpdateHeading"
          v-text="$t('coopcycleApp.restaurant.home.createOrEditLabel')"
        >
          Create or edit a Restaurant
        </h2>
        <div>
          <div class="form-group" v-if="restaurant.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="restaurant.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.restaurant.nom')" for="restaurant-nom">Nom</label>
            <input
              type="text"
              class="form-control"
              name="nom"
              id="restaurant-nom"
              data-cy="nom"
              :class="{ valid: !$v.restaurant.nom.$invalid, invalid: $v.restaurant.nom.$invalid }"
              v-model="$v.restaurant.nom.$model"
              required
            />
            <div v-if="$v.restaurant.nom.$anyDirty && $v.restaurant.nom.$invalid">
              <small class="form-text text-danger" v-if="!$v.restaurant.nom.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.restaurant.adresse')" for="restaurant-adresse">Adresse</label>
            <input
              type="text"
              class="form-control"
              name="adresse"
              id="restaurant-adresse"
              data-cy="adresse"
              :class="{ valid: !$v.restaurant.adresse.$invalid, invalid: $v.restaurant.adresse.$invalid }"
              v-model="$v.restaurant.adresse.$model"
              required
            />
            <div v-if="$v.restaurant.adresse.$anyDirty && $v.restaurant.adresse.$invalid">
              <small class="form-text text-danger" v-if="!$v.restaurant.adresse.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.restaurant.adresse.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
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
            :disabled="$v.restaurant.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./restaurant-update.component.ts"></script>
