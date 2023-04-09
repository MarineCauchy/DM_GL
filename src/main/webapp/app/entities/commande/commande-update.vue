<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="coopcycleApp.commande.home.createOrEditLabel"
          data-cy="CommandeCreateUpdateHeading"
          v-text="$t('coopcycleApp.commande.home.createOrEditLabel')"
        >
          Create or edit a Commande
        </h2>
        <div>
          <div class="form-group" v-if="commande.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="commande.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.commande.idCo')" for="commande-idCo">Id Co</label>
            <input
              type="number"
              class="form-control"
              name="idCo"
              id="commande-idCo"
              data-cy="idCo"
              :class="{ valid: !$v.commande.idCo.$invalid, invalid: $v.commande.idCo.$invalid }"
              v-model.number="$v.commande.idCo.$model"
              required
            />
            <div v-if="$v.commande.idCo.$anyDirty && $v.commande.idCo.$invalid">
              <small class="form-text text-danger" v-if="!$v.commande.idCo.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.commande.idCo.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.commande.prixTotal')" for="commande-prixTotal">Prix Total</label>
            <input
              type="number"
              class="form-control"
              name="prixTotal"
              id="commande-prixTotal"
              data-cy="prixTotal"
              :class="{ valid: !$v.commande.prixTotal.$invalid, invalid: $v.commande.prixTotal.$invalid }"
              v-model.number="$v.commande.prixTotal.$model"
              required
            />
            <div v-if="$v.commande.prixTotal.$anyDirty && $v.commande.prixTotal.$invalid">
              <small class="form-text text-danger" v-if="!$v.commande.prixTotal.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.commande.prixTotal.min" v-text="$t('entity.validation.min', { min: 0 })">
                This field should be at least 0.
              </small>
              <small class="form-text text-danger" v-if="!$v.commande.prixTotal.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.commande.date')" for="commande-date">Date</label>
            <div class="d-flex">
              <input
                id="commande-date"
                data-cy="date"
                type="datetime-local"
                class="form-control"
                name="date"
                :class="{ valid: !$v.commande.date.$invalid, invalid: $v.commande.date.$invalid }"
                required
                :value="convertDateTimeFromServer($v.commande.date.$model)"
                @change="updateInstantField('date', $event)"
              />
            </div>
            <div v-if="$v.commande.date.$anyDirty && $v.commande.date.$invalid">
              <small class="form-text text-danger" v-if="!$v.commande.date.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.commande.date.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.commande.adresseLivraison')" for="commande-adresseLivraison"
              >Adresse Livraison</label
            >
            <input
              type="text"
              class="form-control"
              name="adresseLivraison"
              id="commande-adresseLivraison"
              data-cy="adresseLivraison"
              :class="{ valid: !$v.commande.adresseLivraison.$invalid, invalid: $v.commande.adresseLivraison.$invalid }"
              v-model="$v.commande.adresseLivraison.$model"
              required
            />
            <div v-if="$v.commande.adresseLivraison.$anyDirty && $v.commande.adresseLivraison.$invalid">
              <small class="form-text text-danger" v-if="!$v.commande.adresseLivraison.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.commande.client')" for="commande-client">Client</label>
            <select class="form-control" id="commande-client" data-cy="client" name="client" v-model="commande.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="commande.client && clientOption.id === commande.client.id ? commande.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('coopcycleApp.commande.livreur')" for="commande-livreur">Livreur</label>
            <select class="form-control" id="commande-livreur" data-cy="livreur" name="livreur" v-model="commande.livreur">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="commande.livreur && livreurOption.id === commande.livreur.id ? commande.livreur : livreurOption"
                v-for="livreurOption in livreurs"
                :key="livreurOption.id"
              >
                {{ livreurOption.id }}
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
            :disabled="$v.commande.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./commande-update.component.ts"></script>
