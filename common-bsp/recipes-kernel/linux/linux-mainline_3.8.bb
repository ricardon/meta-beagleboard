require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(beaglebone)"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "a"

FILESPATH =. "${FILE_DIRNAME}/linux-mainline-3.8:${FILE_DIRNAME}/linux-mainline-3.8/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.8.0"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;branch=master"
SRCREV_pn-${PN} = "19f949f52599ba7c3f67a5897ac6be14bfcb1200"

do_configure_prepend() {
	if [ -e ${WORKDIR}/am335x-pm-firmware.bin ] ; then
		cp ${WORKDIR}/am335x-pm-firmware.bin ${S}/firmware
	fi
}

SRC_URI += " \
	file://dma/0001-Without-MACH_-option-Early-printk-DEBUG_LL.patch \
	file://dma/0002-ARM-OMAP-Hack-AM33xx-clock-data-to-allow-JTAG-use.patch \
	file://dma/0003-fb-Rework-locking-to-fix-lock-ordering-on-takeover.patch \
	file://dma/0004-video-st7735fb-add-st7735-framebuffer-driver.patch \
	file://dma/0005-dmaengine-add-helper-function-to-request-a-slave-DMA.patch \
	file://dma/0006-of-Add-generic-device-tree-DMA-helpers.patch \
	file://dma/0007-of-dma-fix-build-break-for-CONFIG_OF.patch \
	file://dma/0008-of-dma-fix-typos-in-generic-dma-binding-definition.patch \
	file://dma/0009-dmaengine-fix-build-failure-due-to-missing-semi-colo.patch \
	file://dma/0010-dmaengine-edma-fix-slave-config-dependency-on-direct.patch \
	file://dma/0011-dmaengine-add-dma_get_channel_caps.patch \
	file://dma/0012-dma-edma-add-device_channel_caps-support.patch \
	file://dma/0013-mmc-davinci-get-SG-segment-limits-with-dma_get_chann.patch \
	file://dma/0014-ARM-davinci-move-private-EDMA-API-to-arm-common.patch \
	file://dma/0015-ARM-edma-remove-unused-transfer-controller-handlers.patch \
	file://dma/0016-ARM-edma-add-AM33XX-support-to-the-private-EDMA-API.patch \
	file://dma/0017-dmaengine-edma-enable-build-for-AM33XX.patch \
	file://dma/0018-dmaengine-edma-Add-TI-EDMA-device-tree-binding.patch \
	file://dma/0019-ARM-dts-add-AM33XX-EDMA-support.patch \
	file://dma/0020-dmaengine-add-dma_request_slave_channel_compat.patch \
	file://dma/0021-mmc-omap_hsmmc-convert-to-dma_request_slave_channel_.patch \
	file://dma/0022-mmc-omap_hsmmc-set-max_segs-based-on-dma-engine-limi.patch \
	file://dma/0023-mmc-omap_hsmmc-add-generic-DMA-request-support-to-th.patch \
	file://dma/0024-ARM-dts-add-AM33XX-MMC-support.patch \
	file://dma/0025-spi-omap2-mcspi-convert-to-dma_request_slave_channel.patch \
	file://dma/0026-spi-omap2-mcspi-add-generic-DMA-request-support-to-t.patch \
	file://dma/0027-ARM-dts-add-AM33XX-SPI-DMA-support.patch \
	file://dma/0028-ARM-dts-Add-SPI-Flash-support-to-am335x-evm.patch \
	file://dma/0029-Documentation-bindings-add-spansion.patch \
	file://dma/0030-ARM-dts-enable-spi1-node-and-pinmux-on-BeagleBone.patch \
	file://dma/0031-ARM-dts-add-BeagleBone-Adafruit-1.8-LCD-support.patch \
	file://dma/0032-misc-add-gpevt-driver.patch \
	file://dma/0033-ARM-dts-add-BeagleBone-gpevt-support.patch \
	file://dma/0034-ARM-configs-working-dmaengine-configs-for-da8xx-and-.patch \
	file://dma/0035-ARM-dts-Add-UART4-support-to-BeagleBone.patch \
	file://rtc/0001-ARM-OMAP2-am33xx-hwmod-Fix-register-offset-NULL-chec.patch \
	file://rtc/0002-rtc-OMAP-Add-system-pm_power_off-to-rtc-driver.patch \
	file://rtc/0003-ARM-dts-AM33XX-Set-pmic-shutdown-controller-for-Beag.patch \
	file://rtc/0004-ARM-dts-AM33XX-Enable-system-power-off-control-in-am.patch \
	file://pinctrl/0001-i2c-pinctrl-ify-i2c-omap.c.patch \
	file://pinctrl/0002-arm-dts-AM33XX-Configure-pinmuxs-for-user-leds-contr.patch \
	file://pinctrl/0003-beaglebone-DT-set-default-triggers-for-LEDS.patch \
	file://pinctrl/0004-beaglebone-add-a-cpu-led-trigger.patch \
	file://cpufreq/0001-am33xx-DT-add-commented-out-OPP-values-for-ES2.0.patch \
	file://adc/0001-input-ti_am335x_tsc-Step-enable-bits-made-configurab.patch \
	file://adc/0002-input-ti_am335x_tsc-Order-of-TSC-wires-made-configur.patch \
	file://adc/0003-input-touchscreen-ti_tsc-remove-unwanted-fifo-flush.patch \
	file://adc/0004-MFD-ti_am335x_tscadc-add-device-tree-binding-informa.patch \
	file://adc/0005-MFD-ti_am335x_tscadc-Add-DT-support.patch \
	file://adc/0006-input-ti_am335x_tsc-Add-DT-support.patch \
	file://adc/0007-IIO-ti_am335x_adc-Add-DT-support.patch \
	file://adc/0008-arm-dts-AM335x-evm-Add-TSC-ADC-MFD-device-support.patch \
	file://adc/0009-ti_tscadc-Update-with-IIO-map-interface-deal-with-pa.patch \
	file://adc/0010-ti_tscadc-Match-mfd-sub-devices-to-regmap-interface.patch \
	file://adc/0011-input-ti_am335x_tsc-Add-variance-filters.patch \
	file://adc/0012-am335x-adc-Do-not-use-find_node_by_name-use-get_chil.patch \
	file://adc/0013-am335x-tsc-Do-not-use-find_node_by_name-use-get_chil.patch \
	file://adc/0014-am335x-tscadc-Do-not-use-find_node_by_name-use-get_c.patch \
	file://i2c/0001-pinctrl-pinctrl-single-must-be-initialized-early.patch \
	file://i2c/0002-Bone-DTS-working-i2c2-i2c3-in-the-tree.patch \
	file://i2c/0003-am33xx-Convert-I2C-from-omap-to-am33xx-names.patch \
	file://i2c/0004-am335x-evm-hack-around-i2c-node-names.patch \
	file://da8xx-fb/0001-viafb-rename-display_timing-to-via_display_timing.patch \
	file://da8xx-fb/0002-video-add-display_timing-and-videomode.patch \
	file://da8xx-fb/0003-video-add-of-helper-for-display-timings-videomode.patch \
	file://da8xx-fb/0004-fbmon-add-videomode-helpers.patch \
	file://da8xx-fb/0005-fbmon-add-of_videomode-helpers.patch \
	file://da8xx-fb/0006-drm_modes-add-videomode-helpers.patch \
	file://da8xx-fb/0007-drm_modes-add-of_videomode-helpers.patch \
	file://da8xx-fb/0008-fbmon-fix-build-error.patch \
	file://da8xx-fb/0009-of-display-timings-use-of_get_child_by_name.patch \
	file://da8xx-fb/0010-da8xx-Allow-use-by-am33xx-based-devices.patch \
	file://da8xx-fb/0011-video-da8xx-fb-fb_check_var-enhancement.patch \
	file://da8xx-fb/0012-video-da8xx-fb-simplify-lcd_reset.patch \
	file://da8xx-fb/0013-video-da8xx-fb-use-modedb-helper-to-update-var.patch \
	file://da8xx-fb/0014-video-da8xx-fb-remove-unneeded-var-initialization.patch \
	file://da8xx-fb/0015-video-da8xx-fb-store-current-display-information.patch \
	file://da8xx-fb/0016-video-da8xx-fb-store-clk-rate-even-if-CPUFREQ.patch \
	file://da8xx-fb/0017-video-da8xx-fb-pix-clk-and-clk-div-handling-cleanup.patch \
	file://da8xx-fb/0018-video-da8xx-fb-store-struct-device.patch \
	file://da8xx-fb/0019-video-da8xx-fb-report-correct-pixclock.patch \
	file://da8xx-fb/0020-video-da8xx-fb-fb_set_par-support.patch \
	file://da8xx-fb/0021-ARM-dts-AM33XX-Add-lcdc-node.patch \
	file://da8xx-fb/0022-ARM-dts-AM33XX-Add-am335x-evm-lcdc-panel-timings.patch \
	file://da8xx-fb/0023-ARM-dts-AM33XX-Add-am335x-evm-lcdc-pincontrol-info.patch \
	file://da8xx-fb/0024-ARM-dts-AM33XX-Add-am335x-evmsk-lcdc-panel-timings.patch \
	file://da8xx-fb/0025-ARM-dts-AM33XX-Add-am335x-evmsk-lcdc-pincontrol-info.patch \
	file://da8xx-fb/0026-ARM-OMAP-AM33xx-hwmod-Corrects-PWM-subsystem-HWMOD-e.patch \
	file://da8xx-fb/0027-ARM-OMAP-AM33xx-hwmod-Add-parent-child-relationship-.patch \
	file://da8xx-fb/0028-ARM-dts-AM33XX-Add-PWMSS-device-tree-nodes.patch \
	file://da8xx-fb/0029-ARM-dts-AM33XX-Add-PWM-backlight-DT-data-to-am335x-e.patch \
	file://da8xx-fb/0030-ARM-dts-AM33XX-Add-PWM-backlight-DT-data-to-am335x-e.patch \
	file://da8xx-fb/0031-clk-divider-prepare-for-minimum-divider.patch \
	file://da8xx-fb/0032-clk-divider-handle-minimum-divider.patch \
	file://da8xx-fb/0033-ARM-OMAP2-dpll-round-rate-to-closest-value.patch \
	file://da8xx-fb/0034-ARM-OMAP2-dpll-am335x-avoid-freqsel.patch \
	file://da8xx-fb/0035-ARM-OMAP2-clock-DEFINE_STRUCT_CLK_FLAGS-helper.patch \
	file://da8xx-fb/0036-ARM-AM33XX-clock-SET_RATE_PARENT-in-lcd-path.patch \
	file://da8xx-fb/0037-video-da8xx-fb-make-io-operations-safe.patch \
	file://da8xx-fb/0038-video-da8xx-fb-fix-24bpp-raster-configuration.patch \
	file://da8xx-fb/0039-video-da8xx-fb-enable-sync-lost-intr-for-v2-ip.patch \
	file://da8xx-fb/0040-video-da8xx-fb-use-devres.patch \
	file://da8xx-fb/0041-video-da8xx-fb-ensure-non-null-cfg-in-pdata.patch \
	file://da8xx-fb/0042-video-da8xx-fb-reorganize-panel-detection.patch \
	file://da8xx-fb/0043-video-da8xx-fb-minimal-dt-support.patch \
	file://da8xx-fb/0044-video-da8xx-fb-invoke-platform-callback-safely.patch \
	file://da8xx-fb/0045-video-da8xx-fb-obtain-fb_videomode-info-from-dt.patch \
	file://da8xx-fb/0046-video-da8xx-fb-ensure-pdata-only-for-non-dt.patch \
	file://da8xx-fb/0047-video-da8xx-fb-setup-struct-lcd_ctrl_config-for-dt.patch \
	file://da8xx-fb/0048-video-da8xx-fb-CCF-clock-divider-handling.patch \
	file://pwm/0001-pwm_backlight-Add-device-tree-support-for-Low-Thresh.patch \
	file://pwm/0002-Control-module-EHRPWM-clk-enabling.patch \
	file://pwm/0003-pwm-pwm_test-Driver-support-for-PWM-module-testing.patch \
	file://pwm/0004-ARM-OMAP2-PWM-limit-am33xx_register_ehrpwm-to-soc_is.patch \
	file://pwm/0005-pwm-export-of_pwm_request.patch \
	file://pwm/0006-pwm-pwm-tiehrpwm-Update-the-clock-handling-of-pwm-ti.patch \
	file://pwm/0007-ARM-AM33XX-clk-Add-clock-node-for-EHRPWM-TBCLK.patch \
	file://pwm/0008-HACK-am33xx.dtsi-turn-on-all-PWMs.patch \
	file://mmc/0001-am33xx.dtsi-enable-MMC-HSPE-bit-for-all-3-controller.patch \
	file://mmc/0002-omap-hsmmc-Correct-usage-of-of_find_node_by_name.patch \
	file://mmc/0003-mmc-core-expose-RPMB-partition-only-for-CMD23-capabl.patch \
	file://crypto/0001-ARM-OMAP2xxx-hwmod-Convert-SHAM-crypto-device-data-t.patch \
	file://crypto/0002-ARM-OMAP2xxx-hwmod-Add-DMA-support-for-SHAM-module.patch \
	file://crypto/0003-ARM-OMAP3xxx-hwmod-Convert-SHAM-crypto-device-data-t.patch \
	file://crypto/0004-ARM-OMAP2-Remove-unnecessary-message-when-no-SHA-IP-.patch \
	file://crypto/0005-ARM-OMAP2-Only-manually-add-hwmod-data-when-DT-not-u.patch \
	file://crypto/0006-ARM-AM33XX-Add-sha0-crypto-clock-data.patch \
	file://crypto/0007-ARM-AM33XX-hwmod-Update-and-uncomment-SHA0-module-da.patch \
	file://crypto/0008-ARM-dts-Add-SHAM-data-and-documentation-for-AM33XX.patch \
	file://crypto/0009-ARM-OMAP2xxx-hwmod-Convert-AES-crypto-devcie-data-to.patch \
	file://crypto/0010-ARM-OMAP3xxx-hwmod-Convert-AES-crypto-device-data-to.patch \
	file://crypto/0011-ARM-OMAP2-Remove-unnecessary-message-when-no-AES-IP-.patch \
	file://crypto/0012-ARM-OMAP2-Only-manually-add-hwmod-data-when-DT-not-u.patch \
	file://crypto/0013-ARM-AM33XX-Add-aes0-crypto-clock-data.patch \
	file://crypto/0014-ARM-AM33XX-hwmod-Update-and-uncomment-AES0-module-da.patch \
	file://crypto/0015-ARM-dts-Add-AES-data-and-documentation-for-AM33XX.patch \
	file://crypto/0016-crypto-omap-sham-Remove-unnecessary-pr_info-noise.patch \
	file://crypto/0017-crypto-omap-sham-Convert-to-use-pm_runtime-API.patch \
	file://crypto/0018-crypto-omap-sham-Add-suspend-resume-support.patch \
	file://crypto/0019-crypto-omap-sham-Add-code-to-use-dmaengine-API.patch \
	file://crypto/0020-crypto-omap-sham-Remove-usage-of-private-DMA-API.patch \
	file://crypto/0021-crypto-omap-sham-Add-Device-Tree-Support.patch \
	file://crypto/0022-crypto-omap-sham-Convert-to-dma_request_slave_channe.patch \
	file://crypto/0023-crypto-omap-sham-Add-OMAP4-AM33XX-SHAM-Support.patch \
	file://crypto/0024-crypto-omap-sham-Add-SHA224-and-SHA256-Support.patch \
	file://crypto/0025-crypto-omap-aes-Remmove-unnecessary-pr_info-noise.patch \
	file://crypto/0026-crypto-omap-aes-Don-t-reset-controller-for-every-ope.patch \
	file://crypto/0027-crypto-omap-aes-Convert-to-use-pm_runtime-API.patch \
	file://crypto/0028-crypto-omap-aes-Add-suspend-resume-support.patch \
	file://crypto/0029-crypto-omap-aes-Add-code-to-use-dmaengine-API.patch \
	file://crypto/0030-crypto-omap-aes-Remove-usage-of-private-DMA-API.patch \
	file://crypto/0031-crypto-omap-aes-Add-Device-Tree-Support.patch \
	file://crypto/0032-crypto-omap-aes-Convert-to-dma_request_slave_channel.patch \
	file://crypto/0033-crypto-omap-aes-Add-OMAP4-AM33XX-AES-Support.patch \
	file://crypto/0034-crypto-omap-aes-Add-CTR-algorithm-Support.patch \
	file://6lowpan/0001-6lowpan-lowpan_is_iid_16_bit_compressable-does-not-d.patch \
	file://6lowpan/0002-6lowpan-next-header-is-not-properly-set-upon-decompr.patch \
	file://6lowpan/0003-6lowpan-always-enable-link-layer-acknowledgments.patch \
	file://6lowpan/0004-mac802154-turn-on-ACK-when-enabled-by-the-upper-laye.patch \
	file://6lowpan/0005-6lowpan-use-short-IEEE-802.15.4-addresses-for-broadc.patch \
	file://6lowpan/0006-6lowpan-fix-first-fragment-FRAG1-handling.patch \
	file://6lowpan/0007-6lowpan-store-fragment-tag-values-per-device-instead.patch \
	file://6lowpan/0008-6lowpan-obtain-IEEE802.15.4-sequence-number-from-the.patch \
	file://6lowpan/0009-6lowpan-add-a-new-parameter-in-sysfs-to-turn-on-off-.patch \
	file://6lowpan/0010-6lowpan-use-the-PANID-provided-by-the-device-instead.patch \
	file://6lowpan/0011-6lowpan-modify-udp-compression-uncompression-to-matc.patch \
	file://6lowpan/0012-6lowpan-make-memory-allocation-atomic-during-6lowpan.patch \
	file://6lowpan/0013-mac802154-make-mem-alloc-ATOMIC-to-prevent-schedulin.patch \
	file://6lowpan/0014-mac802154-remove-unnecessary-spinlocks.patch \
	file://6lowpan/0015-mac802154-re-introduce-MAC-primitives-required-to-se.patch \
	file://6lowpan/0016-serial-initial-import-of-the-IEEE-802.15.4-serial-dr.patch \
	file://capebus/0001-gpio-keys-Pinctrl-fy.patch \
	file://capebus/0002-tps65217-Allow-placement-elsewhere-than-parent-mfd-d.patch \
	file://capebus/0003-pwm-backlight-Pinctrl-fy.patch \
	file://capebus/0004-ARM-CUSTOM-Build-a-uImage-with-dtb-already-appended.patch \
	file://capebus/0005-beaglebone-create-a-shared-dtsi-for-beaglebone-based.patch \
	file://capebus/0006-beaglebone-enable-emmc-for-bonelt.patch \
	file://capebus/0007-Fix-appended-dtb-rule.patch \
	file://arm/0001-kbuild-deb-pkg-set-host-machine-after-dpkg-gencontro.patch \
	file://arm/0002-arm-add-definition-of-strstr-to-decompress.c.patch \
	file://arm/0003-Without-MACH_-option-Early-printk-DEBUG_LL.patch \
	file://omap/0001-regulator-core-if-voltage-scaling-fails-restore-orig.patch \
	file://omap/0002-omap2-twl-common-Add-default-power-configuration.patch \
	file://omap_sakoman/0001-OMAP-DSS2-add-bootarg-for-selecting-svideo.patch \
	file://omap_sakoman/0002-video-add-timings-for-hd720.patch \
	file://omap_beagle_expansion/0001-Beagle-expansion-add-buddy-param-for-expansionboard-.patch \
	file://omap_beagle_expansion/0002-Beagle-expansion-add-zippy.patch \
	file://omap_beagle_expansion/0003-Beagle-expansion-add-zippy2.patch \
	file://omap_beagle_expansion/0004-Beagle-expansion-add-trainer.patch \
	file://omap_beagle_expansion/0005-Beagle-expansion-add-CircuitCo-ulcd-Support.patch \
	file://omap_beagle_expansion/0006-Beagle-expansion-add-wifi.patch \
	file://omap_beagle_expansion/0007-Beagle-expansion-add-beaglefpga.patch \
	file://omap_beagle_expansion/0008-Beagle-expansion-add-spidev.patch \
	file://omap_beagle_expansion/0009-Beagle-expansion-add-Aptina-li5m03-camera.patch \
	file://omap_beagle_expansion/0010-Beagle-expansion-add-LSR-COM6L-Adapter-Board.patch \
	file://omap_beagle/0001-meego-modedb-add-Toshiba-LTA070B220F-800x480-support.patch \
	file://omap_beagle/0002-backlight-Add-TLC59108-backlight-control-driver.patch \
	file://omap_beagle/0003-tlc59108-adjust-for-beagleboard-uLCD7.patch \
	file://omap_beagle/0004-zeroMAP-Open-your-eyes.patch \
	file://omap_beagle/0005-ARM-OMAP-Beagle-use-TWL4030-generic-reset-script.patch \
	file://omap_panda/0001-panda-fix-wl12xx-regulator.patch \
	file://omap_panda/0002-ti-st-st-kim-fixing-firmware-path.patch \
	file://net/0001-am33xx-cpsw-default-to-ethernet-hwaddr-from-efuse-if.patch \
	file://net/0002-Attempted-SMC911x-BQL-patch.patch \
	file://net/0003-cpsw-Fix-interrupt-storm-among-other-things.patch \
	file://drm/0001-am33xx-Add-clock-for-the-lcdc-DRM-driver.patch \
	file://drm/0002-drm-small-fix-in-drm_send_vblank_event.patch \
	file://drm/0003-drm-cma-add-debugfs-helpers.patch \
	file://drm/0004-drm-i2c-encoder-helper-wrappers.patch \
	file://drm/0005-drm-nouveau-use-i2c-encoder-helper-wrappers.patch \
	file://drm/0006-drm-i2c-give-i2c-it-s-own-Kconfig.patch \
	file://drm/0007-drm-tilcdc-add-TI-LCD-Controller-DRM-driver-v4.patch \
	file://drm/0008-drm-i2c-nxp-tda998x-v3.patch \
	file://drm/0009-drm-tilcdc-add-encoder-slave.patch \
	file://drm/0010-drm-tilcdc-add-support-for-LCD-panels-v5.patch \
	file://drm/0011-drm-lcdc-Power-control-GPIO-support.patch \
	file://not-capebus/0001-add-dvi-pinmuxes-to-am33xx.dtsi.patch \
	file://not-capebus/0002-add-defconfig-file-to-use-as-.config.patch \
	file://not-capebus/0003-am33xx-musb-Add-OF-definitions.patch \
	file://not-capebus/0004-Mark-the-device-as-PRIVATE.patch \
	file://not-capebus/0005-omap_hsmmc-Bug-fixes-pinctl-gpio-reset.patch \
	file://not-capebus/0006-tps65217-bl-Locate-backlight-node-correctly.patch \
	file://not-capebus/0007-arm-Export-cache-flush-management-symbols-when-MULTI.patch \
	file://not-capebus/0008-am335x-bone-dtsi-Clean-up.patch \
	file://not-capebus/0009-am335x-bone-dtsi-Introduce-new-I2C-entries.patch \
	file://not-capebus/0010-am335x-dt-Add-I2C0-pinctrl-entries.patch \
	file://not-capebus/0011-Cleanup-am33xx.dtsi.patch \
	file://not-capebus/0012-Fix-platform-device-resource-linking.patch \
	file://not-capebus/0013-Link-platform-device-resources-properly.patch \
	file://not-capebus/0014-Properly-handle-resources-for-omap_devices.patch \
	file://not-capebus/0015-omap-Avoid-crashes-in-the-case-of-hwmod-misconfigura.patch \
	file://not-capebus/0016-i2c-EEPROM-In-kernel-memory-accessor-interface.patch \
	file://not-capebus/0017-Fix-util_is_printable_string.patch \
	file://not-capebus/0018-fdtdump-properly-handle-multi-string-properties.patch \
	file://not-capebus/0019-dtc-Dynamic-symbols-fixup-support.patch \
	file://not-capebus/0020-dtc-Add-DTCO-rule-for-DTB-objects.patch \
	file://not-capebus/0021-OF-Compile-Device-Tree-sources-with-resolve-option.patch \
	file://not-capebus/0022-firmware-update-.gitignore-with-dtbo-objects.patch \
	file://not-capebus/0023-OF-Introduce-device-tree-node-flag-helpers.patch \
	file://not-capebus/0024-OF-export-of_property_notify.patch \
	file://not-capebus/0025-OF-Export-all-DT-proc-update-functions.patch \
	file://not-capebus/0026-OF-Introduce-utility-helper-functions.patch \
	file://not-capebus/0027-OF-Introduce-Device-Tree-resolve-support.patch \
	file://not-capebus/0028-OF-Introduce-DT-overlay-support.patch \
	file://not-capebus/0029-capemgr-Capemgr-makefiles-and-Kconfig-fragments.patch \
	file://not-capebus/0030-capemgr-Beaglebone-capemanager.patch \
	file://not-capebus/0031-capemgr-Add-beaglebone-s-cape-driver-bindings.patch \
	file://not-capebus/0032-capemgr-am33xx-family-DT-bindings.patch \
	file://not-capebus/0033-bone-geiger-Geiger-bone-driver.patch \
	file://not-capebus/0034-capemgr-firmware-makefiles-for-DT-objects.patch \
	file://not-capebus/0035-capemgr-emmc2-cape-definition.patch \
	file://not-capebus/0036-capemgr-DVI-capes-definitions.patch \
	file://not-capebus/0037-capemgr-Geiger-cape-definition.patch \
	file://not-capebus/0038-capemgr-LCD3-cape-definition.patch \
	file://not-capebus/0039-capemgr-Add-weather-cape-definition.patch \
	file://not-capebus/0040-ehrpwm-add-missing-dts-nodes.patch \
	file://not-capebus/0041-am33xx-DT-Update-am33xx.dsi-with-the-new-PWM-DT-bind.patch \
	file://not-capebus/0042-geiger-cape-Update-to-using-the-new-PWM-interface.patch \
	file://not-capebus/0043-lcd3-cape-Change-into-using-the-lcdc-DRM-driver-inst.patch \
	file://not-capebus/0044-am33xx-Add-default-config.patch \
	file://not-capebus/0045-lcd3-cape-Convert-to-using-the-proper-touchscreen-dr.patch \
	file://not-capebus/0046-geiger-cape-Convert-to-using-the-new-ADC-driver.patch \
	file://not-capebus/0047-cape-dvi-Convert-DVI-capes-to-the-new-LCDC-DRM-drive.patch \
	file://not-capebus/0048-boneblack-Add-default-HDMI-cape.patch \
	file://not-capebus/0049-cape-bone-dvi-Use-720p-mode-as-default.patch \
	file://not-capebus/0050-am33xx.dtsi-Make-the-MUSB-not-crash-on-load.patch \
	file://not-capebus/0051-regulator-DUMMY_REGULATOR-should-work-for-OF-too.patch \
	file://not-capebus/0052-OF-Overlay-Remove-excessive-debugging-crud.patch \
	file://not-capebus/0053-of-i2c-Export-single-device-registration-method.patch \
	file://not-capebus/0054-OF-Overlay-I2C-client-devices-special-handling.patch \
	file://not-capebus/0055-omap-Fix-bug-on-partial-resource-addition.patch \
	file://not-capebus/0056-ASoC-davinci-mcasp-Add-pinctrl-support.patch \
	file://not-capebus/0057-ASoC-Davinci-machine-Add-device-tree-binding.patch \
	file://not-capebus/0058-am33xx-Add-mcasp0-and-mcasp1-device-tree-entries.patch \
	file://not-capebus/0059-ASoC-dts-OMAP2-AM33xx-HACK-Add-missing-dma-info.patch \
	file://not-capebus/0060-ASoC-Davinci-McASP-remove-unused-header-include.patch \
	file://not-capebus/0061-ASoC-AM33XX-Add-support-for-AM33xx-SoC-Audio.patch \
	file://not-capebus/0062-am33xx-mcasp-Add-dma-channel-definitions.patch \
	file://not-capebus/0063-ARM-OMAP2-AM33xx-removed-invalid-McASP-HWMOD-data.patch \
	file://not-capebus/0064-davinci-evm-Header-include-move-fix.patch \
	file://not-capebus/0065-bone-dvi-cape-Add-DT-definition-for-00A2-revision.patch \
	file://not-capebus/0066-bone-dvi-cape-Update-A1-cape-definition-with-sound.patch \
	file://not-capebus/0067-sndsoc-mcasp-Get-DMA-channels-via-byname.patch \
	file://not-capebus/0068-sound-soc-Davinci-Remove-__devinit-__devexit.patch \
	file://not-capebus/0069-st7735fb-Remove-__devinit-__devexit.patch \
	file://not-capebus/0070-capemgr-Remove-__devinit-__devexit.patch \
	file://not-capebus/0071-capes-fw-target-firmware-directory-change.patch \
	file://not-capebus/0072-am33xx-edma-Always-update-unused-channel-list.patch \
	file://not-capebus/0073-defconfig-Update-bone-default-config.patch \
	file://not-capebus/0074-capes-add-dvi-a2-and-lcd3-a2-dts-files.patch \
	file://not-capebus/0075-capemgr-catch-up-with-lcdc-tilcdc-rename.patch \
	file://not-capebus/0076-firmware-fix-dvi-a1-target.patch \
	file://not-capebus/0077-capes-remove-tda-from-hdmi-cape-lcdc-handles-it-by-t.patch \
	file://not-capebus/0078-tilcdc-magic-debug-statement-makes-power-gpio-work-o.patch \
	file://not-capebus/0079-capemgr-add-dts-overlay-for-LCD7-00A2-cape.patch \
	file://not-capebus/0080-HACK-am33xx.dtsi-enable-all-PWMs.patch \
	file://not-capebus/0081-beaglebone-Add-nixie-cape-prototype-driver.patch \
	file://not-capebus/0082-beaglebone-Add-nixie-cape-device-tree-entry.patch \
	file://not-capebus/0083-am335x-bone-common.dtsi-Cleanup-test-remnants.patch \
	file://not-capebus/0084-omap_hsmmc-Add-ti-vcc-aux-disable-is-sleep-DT-proper.patch \
	file://not-capebus/0085-bone-common-ti-vcc-aux-disable-is-sleep-enable.patch \
	file://not-capebus/0086-am33xx-disable-NAPI.patch \
	file://not-capebus/0087-capemgr-Fixed-AIN-name-display-in-error-message.patch \
	file://not-capebus/0088-am33xx.dtsi-remove-duplicate-nodes.patch \
	file://not-capebus/0089-cape-dtbos-update-to-latest-OF-videomode-bindings.patch \
	file://not-capebus/0090-beaglebone-uncomment-eMMC-override.patch \
	file://not-capebus/0091-bone-capes-Update-with-new-tscadc-bindings.patch \
	file://not-capebus/0092-am33xx.dtsi-Update-and-disable-status-of-nodes.patch \
	file://not-capebus/0093-bone-capes-Adapt-to-new-pwms-setup.patch \
	file://not-capebus/0094-tilcdc-introduce-panel-tfp410-power-down-gpio-contro.patch \
	file://not-capebus/0095-bone-dvi-Update-to-new-style-tilcdc-bindings.patch \
	file://not-capebus/0096-tilcdc-tfp410-Rework-power-down-GPIO-logic.patch \
	file://not-capebus/0097-tilcdc-Add-reduced-blanking-mode-checks.patch \
	file://not-capebus/0098-cape-dvi-Switch-all-DVI-capes-to-using-the-TFTP410-p.patch \
	file://pru/0001-uio-uio_pruss-port-to-AM33xx.patch \
	file://pru/0002-ARM-omap-add-DT-support-for-deasserting-hardware-res.patch \
	file://pru/0003-ARM-dts-AM33xx-PRUSS-support.patch \
	file://usb/0001-drivers-usb-phy-add-a-new-driver-for-usb-part-of-con.patch \
	file://usb/0002-drivers-usb-start-using-the-control-module-driver.patch \
	file://usb/0003-usb-otg-Add-an-API-to-bind-the-USB-controller-and-PH.patch \
	file://usb/0004-usb-otg-utils-add-facilities-in-phy-lib-to-support-m.patch \
	file://usb/0005-ARM-OMAP-USB-Add-phy-binding-information.patch \
	file://usb/0006-drivers-usb-musb-omap-make-use-of-the-new-PHY-lib-AP.patch \
	file://usb/0007-usb-otg-add-device-tree-support-to-otg-library.patch \
	file://usb/0008-USB-MUSB-OMAP-get-PHY-by-phandle-for-dt-boot.patch \
	file://usb/0009-MUSB-Hack-around-to-make-host-port-to-work.patch \
	file://PG2/0001-beaglebone-black-1ghz-hack.patch \
	file://reboot/0001-ARM-AM33xx-Add-SoC-specific-restart-hook.patch \
	file://iio/0001-iio-common-Add-STMicroelectronics-common-library.patch \
	file://iio/0002-iio-accel-Add-STMicroelectronics-accelerometers-driv.patch \
	file://iio/0003-iio-gyro-Add-STMicroelectronics-gyroscopes-driver.patch \
	file://iio/0004-iio-magnetometer-Add-STMicroelectronics-magnetometer.patch \
	file://iio/0005-iio-magn-Add-sensors_supported-in-st_magn_sensors.patch \
	file://iio/0006-pwm-pca9685-skeleton-i2c-client-driver-for-PCA9685-1.patch \
	file://defconfig \
  file://am335x-pm-firmware.bin \
"
