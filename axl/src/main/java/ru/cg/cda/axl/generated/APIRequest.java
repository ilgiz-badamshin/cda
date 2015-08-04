
package ru.cg.cda.axl.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * All requests must extend abstractRequest.
 * 
 * <p>Java class for APIRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APIRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="sequence" type="{http://www.w3.org/2001/XMLSchema}unsignedLong" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APIRequest")
@XmlSeeAlso({
    AddImeEnrolledPatternReq.class,
    AddFallbackProfileReq.class,
    AddLineGroupReq.class,
    AddUcServiceReq.class,
    UpdateImeEnrolledPatternReq.class,
    AddDhcpSubnetReq.class,
    AddImeE164TransformationReq.class,
    AddProcessNodeReq.class,
    AddDevicePoolReq.class,
    UpdateMobileVoiceAccessReq.class,
    AddAudioCodecPreferenceListReq.class,
    AddLdapFilterReq.class,
    AddVpnProfileReq.class,
    AddCiscoCatalyst600024PortFXSGatewayReq.class,
    UpdateSNMPCommunityStringReq.class,
    UpdateIvrUserLocaleReq.class,
    UpdateCustomUserFieldReq.class,
    AddDeviceMobilityReq.class,
    UpdateRoutePartitionsForLearnedPatternsReq.class,
    DoAuthenticateUserReq.class,
    AddCommonPhoneConfigReq.class,
    AddVoiceMailProfileReq.class,
    AddDirectoryLookupDialRulesReq.class,
    AddHuntListReq.class,
    AddApplicationDialRulesReq.class,
    UpdateCcdFeatureConfigReq.class,
    UpdateUserReq.class,
    AddIvrUserLocaleReq.class,
    UpdateRoutePatternReq.class,
    UpdateRegionMatrixReq.class,
    ExecuteSQLQueryReq.class,
    AddSipTrunkReq.class,
    AddImeRouteFilterElementReq.class,
    AddUniversalLineTemplateReq.class,
    UpdateRemoteClusterReq.class,
    AddServiceProfileReq.class,
    UpdateServiceParameterReq.class,
    AddRegionReq.class,
    UpdateResourcePriorityNamespaceReq.class,
    AddSdpTransparencyProfileReq.class,
    GetLdapSyncStatusReq.class,
    AddVohServerReq.class,
    AddAppServerInfoReq.class,
    AddImeFirewallReq.class,
    AddSipTrunkSecurityProfileReq.class,
    AddMobilityProfileReq.class,
    DoDeviceLogoutReq.class,
    AddCalledPartyTransformationPatternReq.class,
    AddLdapSyncCustomFieldReq.class,
    UpdateVoiceMailPilotReq.class,
    AddPhysicalLocationReq.class,
    AddPhoneReq.class,
    UpdateHandoffConfigurationReq.class,
    UpdateEmccFeatureConfigReq.class,
    AddCallParkReq.class,
    AddSoftKeyTemplateReq.class,
    AddBlockedLearnedPatternsReq.class,
    AddSafCcdPurgeBlockLearnedRoutesReq.class,
    AddCallPickupGroupReq.class,
    AddSipRoutePatternReq.class,
    AddMeetMeReq.class,
    AddMobilityReq.class,
    AddGatewaySccpEndpointsReq.class,
    AddPhoneSecurityProfileReq.class,
    AddSIPNormalizationScriptReq.class,
    AddRemoteClusterReq.class,
    AddCcdHostedDNReq.class,
    AddImeClientReq.class,
    AddAnnouncementReq.class,
    AddImeEnrolledPatternGroupReq.class,
    AddFeatureGroupTemplateReq.class,
    AddHandoffConfigurationReq.class,
    AddCiscoCatalyst6000E1VoIPGatewayReq.class,
    AddAdvertisedPatternsReq.class,
    AddRemoteDestinationProfileReq.class,
    AddPresenceGroupReq.class,
    GetPageLayoutPreferencesReq.class,
    AddDateTimeGroupReq.class,
    AddRecordingProfileReq.class,
    AddGeoLocationPolicyReq.class,
    AddDefaultDeviceProfileReq.class,
    AddWifiHotspotReq.class,
    DoLdapSyncReq.class,
    AddLdapDirectoryReq.class,
    AddSipDialRulesReq.class,
    AddApplicationToSoftkeyTemplateReq.class,
    AddGatekeeperReq.class,
    AddRouteListReq.class,
    AddMobileVoiceAccessReq.class,
    UpdateCcdHostedDNReq.class,
    AddIpPhoneServicesReq.class,
    UpdatePageLayoutPreferencesReq.class,
    AddFacInfoReq.class,
    AddUserGroupReq.class,
    AddGatewayEndpointDigitalAccessBriReq.class,
    AddPresenceRedundancyGroupReq.class,
    AddConferenceBridgeReq.class,
    GetPhoneTypeDisplayInstanceReq.class,
    AddWLANProfileReq.class,
    AddSipRealmReq.class,
    ExecuteSQLUpdateReq.class,
    AddEnterpriseFeatureAccessConfigurationReq.class,
    AssignPresenceUserReq.class,
    UpdateSNMPMIB2ListReq.class,
    AddSNMPUserReq.class,
    AddDhcpServerReq.class,
    RemoveSNMPCommunityStringReq.class,
    UpdateDhcpServerReq.class,
    AddGatewayReq.class,
    AddVoiceMailPortReq.class,
    ListChangeReq.class,
    UpdateProcessNodeServiceReq.class,
    AddVg224Req.class,
    UpdateSyslogConfigurationReq.class,
    UpdateBlockedLearnedPatternsReq.class,
    UpdateCalledPartyTransformationPatternReq.class,
    AddSafForwarderReq.class,
    AddMediaResourceListReq.class,
    AddLbmHubGroupReq.class,
    AddImeExclusionNumberGroupReq.class,
    UpdateTransPatternReq.class,
    AddApplicationServerReq.class,
    AddCallerFilterListReq.class,
    UpdateSafCcdPurgeBlockLearnedRoutesReq.class,
    AddDirNumberAliasLookupandSyncReq.class,
    AddAarGroupReq.class,
    UpdateCredentialPolicyDefaultReq.class,
    AddTimeScheduleReq.class,
    AddCredentialPolicyReq.class,
    AddVpnGatewayReq.class,
    AddCallingPartyTransformationPatternReq.class,
    AddHuntPilotReq.class,
    GetSNMPMIB2ListReq.class,
    UpdateInterClusterServiceProfileReq.class,
    AddApplicationUserCapfProfileReq.class,
    AddUserPhoneAssociationReq.class,
    AddPhoneButtonTemplateReq.class,
    UpdateApplicationUserCapfProfileReq.class,
    UpdateDirectedCallParkReq.class,
    AddCommonDeviceConfigReq.class,
    AddGatewayEndpointAnalogAccessReq.class,
    AddImeRouteFilterGroupReq.class,
    AddUserProfileProvisionReq.class,
    GetNumDevicesReq.class,
    UpdateBillingServerReq.class,
    AddGatewayEndpointDigitalAccessT1Req.class,
    UpdateIpPhoneServicesReq.class,
    AddRoutePartitionReq.class,
    AddCssReq.class,
    DoDeviceLoginReq.class,
    UpdateGatewayReq.class,
    UpdateIlsConfigReq.class,
    UpdateLocalRouteGroupReq.class,
    AddVoiceMailPilotReq.class,
    AddH323PhoneReq.class,
    AddBillingServerReq.class,
    AddCiscoCatalyst6000T1VoIPGatewayT1Req.class,
    UpdateSipRoutePatternReq.class,
    UpdateAppUserReq.class,
    UpdateCmcInfoReq.class,
    UpdateHuntPilotReq.class,
    UpdateVg224Req.class,
    DoUpdateRemoteClusterReq.class,
    AddCcdRequestingServiceReq.class,
    AddGatewaySubunitsReq.class,
    AddResourcePriorityNamespaceReq.class,
    UpdateMohAudioSourceReq.class,
    AddH323TrunkReq.class,
    AddSipProfileReq.class,
    AddPhoneNtpReq.class,
    AddTodAccessReq.class,
    AddUserReq.class,
    UpdateTvsCertificateReq.class,
    AddGeoLocationFilterReq.class,
    AddAppUserReq.class,
    AddCcdHostedDNGroupReq.class,
    AddSNMPCommunityStringReq.class,
    UpdateSelfProvisioningReq.class,
    AddGatewayEndpointDigitalAccessPriReq.class,
    UpdateEnterpriseFeatureAccessConfigurationReq.class,
    AddCmcInfoReq.class,
    AddUnitsToGatewayReq.class,
    GetImeLearnedRoutesReq.class,
    AddRemoteDestinationReq.class,
    AddLocationReq.class,
    AddDeviceProfileReq.class,
    AddDeviceMobilityGroupReq.class,
    AddMediaResourceGroupReq.class,
    UpdateImeExclusionNumberReq.class,
    AddMtpReq.class,
    UpdateLdapSyncCustomFieldReq.class,
    UpdatePhoneNtpReq.class,
    AddRoutePatternReq.class,
    DoDeviceResetReq.class,
    AddTimePeriodReq.class,
    UpdateMessageWaitingReq.class,
    UpdateAppServerInfoReq.class,
    AddUniversalDeviceTemplateReq.class,
    UpdateAdvertisedPatternsReq.class,
    UpdateRemoteDestinationReq.class,
    GetSNMPCommunityStringReq.class,
    AddNetworkAccessProfileReq.class,
    UpdateDhcpSubnetReq.class,
    UpdateEndUserCapfProfileReq.class,
    UpdateAarGroupMatrixReq.class,
    UpdateCallPickupGroupReq.class,
    AddCCAProfilesReq.class,
    UpdateImeLearnedRoutesReq.class,
    AddFeatureControlPolicyReq.class,
    AddCumaServerSecurityProfileReq.class,
    AddSafSecurityProfileReq.class,
    AddLbmGroupReq.class,
    AddVpnGroupReq.class,
    UpdateSNMPUserReq.class,
    AddImeExclusionNumberReq.class,
    UpdateSipRealmReq.class,
    AddEndUserCapfProfileReq.class,
    UnassignPresenceUserReq.class,
    UpdateApplicationServerReq.class,
    UpdateLineReq.class,
    UpdateMlppDomainReq.class,
    AddGeoLocationReq.class,
    AddCcdAdvertisingServiceReq.class,
    AddImeServerReq.class,
    AddCtiRoutePointReq.class,
    UpdateInterClusterDirectoryUriReq.class,
    AddTransPatternReq.class,
    UpdateCallingPartyTransformationPatternReq.class,
    AddImportedDirectoryUriCatalogsReq.class,
    UpdateMeetMeReq.class,
    UpdateCallParkReq.class,
    AddSrstReq.class,
    AddLocalRouteGroupReq.class,
    AddExternalCallControlProfileReq.class,
    AddDirectedCallParkReq.class,
    AddTransformationProfileReq.class,
    AddWlanProfileGroupReq.class,
    AddCiscoCatalyst6000T1VoIPGatewayPriReq.class,
    AddResourcePriorityNamespaceListReq.class,
    AddH323GatewayReq.class,
    AddCalledPartyTracingReq.class,
    GetSNMPUserReq.class,
    AddRouteGroupReq.class,
    AddRouteFilterReq.class,
    RemoveSNMPUserReq.class,
    AddCallManagerGroupReq.class,
    AddLineReq.class,
    AddMessageWaitingReq.class,
    UpdateCCAProfilesReq.class,
    AddCustomUserFieldReq.class,
    AddTranscoderReq.class,
    AddMlppDomainReq.class,
    NameAndGUIDRequest.class,
    DoChangeDNDStatusReq.class
})
public abstract class APIRequest {

    @XmlAttribute(name = "sequence")
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger sequence;

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequence(BigInteger value) {
        this.sequence = value;
    }

}
