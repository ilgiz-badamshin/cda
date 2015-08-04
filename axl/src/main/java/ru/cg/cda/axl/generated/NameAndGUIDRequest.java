
package ru.cg.cda.axl.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * This is a utility object that created because many of the Get, Remove, and Update methods allow the user to specify either the name or the uuid.
 * 
 * <p>Java class for NameAndGUIDRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NameAndGUIDRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.cisco.com/AXL/API/10.0}APIRequest">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="name" type="{http://www.cisco.com/AXL/API/10.0}String100"/>
 *           &lt;element name="uuid" type="{http://www.cisco.com/AXL/API/10.0}XUUID"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameAndGUIDRequest", propOrder = {
    "name",
    "uuid"
})
@XmlSeeAlso({
    UpdateSoftKeySetReq.class,
    UpdateGeoLocationFilterReq.class,
    UpdateUserGroupReq.class,
    UpdateProcessNodeReq.class,
    UpdateVoiceMailProfileReq.class,
    UpdateFeatureControlPolicyReq.class,
    UpdateFallbackProfileReq.class,
    UpdateSipTrunkSecurityProfileReq.class,
    UpdateSipDialRulesReq.class,
    UpdateRouteFilterReq.class,
    UpdateMediaResourceListReq.class,
    UpdateImeRouteFilterGroupReq.class,
    UpdateLdapDirectoryReq.class,
    UpdateRouteListReq.class,
    UpdatePhoneSecurityProfileReq.class,
    UpdateImeServerReq.class,
    UpdateFixedMohAudioSourceReq.class,
    UpdateTimeScheduleReq.class,
    UpdateRecordingProfileReq.class,
    UpdateLineGroupReq.class,
    UpdateUserProfileProvisionReq.class,
    UpdateUniversalDeviceTemplateReq.class,
    UpdateGatewayEndpointDigitalAccessBriReq.class,
    UpdateVpnGroupReq.class,
    UpdateCcdRequestingServiceReq.class,
    UpdateAudioCodecPreferenceListReq.class,
    UpdateResourcePriorityNamespaceListReq.class,
    UpdateHuntListReq.class,
    UpdateRegionReq.class,
    UpdateSdpTransparencyProfileReq.class,
    UpdateServiceProfileReq.class,
    UpdateRoutePartitionReq.class,
    UpdateDeviceMobilityGroupReq.class,
    UpdateCiscoCatalyst600024PortFXSGatewayReq.class,
    UpdateMediaResourceGroupReq.class,
    UpdateImeE164TransformationReq.class,
    UpdateSafSecurityProfileReq.class,
    UpdateVpnProfileReq.class,
    UpdateTransformationProfileReq.class,
    UpdateGatewaySccpEndpointsReq.class,
    UpdateWLANProfileReq.class,
    UpdatePresenceRedundancyGroupReq.class,
    UpdateDateTimeGroupReq.class,
    UpdateCredentialPolicyReq.class,
    UpdateImeClientReq.class,
    UpdatePresenceGroupReq.class,
    UpdateRemoteDestinationProfileReq.class,
    UpdateCommonDeviceConfigReq.class,
    UpdateLocationReq.class,
    UpdateFeatureGroupTemplateReq.class,
    UpdatePhoneReq.class,
    UpdatePhysicalLocationReq.class,
    UpdateDirectoryLookupDialRulesReq.class,
    UpdateAarGroupReq.class,
    UpdateCcdAdvertisingServiceReq.class,
    UpdateImeEnrolledPatternGroupReq.class,
    UpdateCiscoCatalyst6000T1VoIPGatewayT1Req.class,
    UpdateImportedDirectoryUriCatalogsReq.class,
    UpdateLbmGroupReq.class,
    UpdateCiscoCatalyst6000E1VoIPGatewayReq.class,
    UpdateTimePeriodReq.class,
    UpdateTranscoderReq.class,
    UpdateGatewayEndpointDigitalAccessT1Req.class,
    UpdateVoiceMailPortReq.class,
    UpdateSipTrunkReq.class,
    UpdateDefaultDeviceProfileReq.class,
    UpdateImeRouteFilterElementReq.class,
    UpdateDeviceMobilityReq.class,
    UpdateCcdHostedDNGroupReq.class,
    UpdateH323GatewayReq.class,
    UpdateGatekeeperReq.class,
    UpdateSIPNormalizationScriptReq.class,
    UpdateLbmHubGroupReq.class,
    UpdateUniversalLineTemplateReq.class,
    UpdateGatewayEndpointAnalogAccessReq.class,
    UpdateDirNumberAliasLookupandSyncReq.class,
    UpdateGeoLocationPolicyReq.class,
    UpdateCallerFilterListReq.class,
    UpdateGeoLocationReq.class,
    UpdateCtiRoutePointReq.class,
    UpdateWifiHotspotReq.class,
    UpdateExternalCallControlProfileReq.class,
    UpdateConferenceBridgeReq.class,
    UpdateSipProfileReq.class,
    UpdateCssReq.class,
    UpdateMohServerReq.class,
    UpdateCallManagerReq.class,
    UpdateAnnouncementReq.class,
    UpdateDeviceProfileReq.class,
    UpdateWlanProfileGroupReq.class,
    UpdateLdapFilterReq.class,
    UpdateSoftKeyTemplateReq.class,
    UpdateImeExclusionNumberGroupReq.class,
    UpdateCallManagerGroupReq.class,
    UpdateNetworkAccessProfileReq.class,
    UpdateVohServerReq.class,
    UpdateCumaServerSecurityProfileReq.class,
    UpdateApplicationDialRulesReq.class,
    UpdateImeFirewallReq.class,
    UpdateCommonPhoneConfigReq.class,
    UpdateAnnunciatorReq.class,
    UpdateVpnGatewayReq.class,
    UpdateUcServiceReq.class,
    UpdateMtpReq.class,
    UpdateGatewayEndpointDigitalAccessPriReq.class,
    UpdateCiscoCatalyst6000T1VoIPGatewayPriReq.class,
    UpdateTodAccessReq.class,
    UpdateDevicePoolReq.class,
    UpdateSrstReq.class,
    UpdateFacInfoReq.class,
    UpdateMobilityProfileReq.class,
    UpdateH323PhoneReq.class,
    UpdateH323TrunkReq.class,
    UpdatePhoneButtonTemplateReq.class,
    UpdateRouteGroupReq.class,
    UpdateSafForwarderReq.class
})
public class NameAndGUIDRequest
    extends APIRequest
{

    protected String name;
    protected String uuid;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
