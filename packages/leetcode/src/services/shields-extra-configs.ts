export type ShieldsExtraConfigs = {
  style: string
  label: string
  labelColor: string
  color: string
  logo: string
  logoColor: string
  logoWidth: string
  link: string
}

export const shieldExtraConfigsToQueryParams = (
  shieldExtraConfigs?: Partial<ShieldsExtraConfigs>
): string => {
  if (shieldExtraConfigs) {
    const queryParamsArray = []
    shieldExtraConfigs.style &&
      queryParamsArray.push(`style=${shieldExtraConfigs.style}`)
    shieldExtraConfigs.label &&
      queryParamsArray.push(`label=${shieldExtraConfigs.label}`)
    shieldExtraConfigs.labelColor &&
      queryParamsArray.push(`labelColor${shieldExtraConfigs.labelColor}`)
    shieldExtraConfigs.color &&
      queryParamsArray.push(`color=${shieldExtraConfigs.color}`)
    shieldExtraConfigs.logo &&
      queryParamsArray.push(`logo=${shieldExtraConfigs.logo}`)
    shieldExtraConfigs.logoColor &&
      queryParamsArray.push(`logoColor=${shieldExtraConfigs.logoColor}`)
    shieldExtraConfigs.logoWidth &&
      queryParamsArray.push(`logoWidth=${shieldExtraConfigs.logoWidth}`)
    shieldExtraConfigs.link &&
      queryParamsArray.push(`link=${shieldExtraConfigs.link}`)
    return `?${queryParamsArray.join('&')}`
  }
  return ''
}
