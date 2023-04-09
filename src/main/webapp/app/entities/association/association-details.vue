<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="association">
        <h2 class="jh-entity-heading" data-cy="associationDetailsHeading">
          <span v-text="$t('coopcycleApp.association.detail.title')">Association</span> {{ association.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('coopcycleApp.association.idA')">Id A</span>
          </dt>
          <dd>
            <span>{{ association.idA }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.association.nomA')">Nom A</span>
          </dt>
          <dd>
            <span>{{ association.nomA }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="association.id"
          :to="{ name: 'AssociationEdit', params: { associationId: association.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./association-details.component.ts"></script>
