import { type Format } from 'badge-maker'

import { ShieldBuilder } from './shields'
import {
  shieldExtraConfigsToQueryParams,
  ShieldsExtraConfigs,
} from './shields-extra-configs'

const usernameFormat = (username: string): string => {
  const format: Format = {
    label: 'Leetcode',
    message: username,
    color: 'orange',
  }
  const formatToString = `${format.label}-${format.message}-${format.color}`
  return formatToString
}

export const usernameBadge = async (
  username: string,
  shieldsExtraConfigs?: Partial<ShieldsExtraConfigs>
): Promise<string> => {
  const shieldBuilder = new ShieldBuilder()
  return shieldBuilder.build(
    username,
    () =>
      usernameFormat(username) +
      shieldExtraConfigsToQueryParams(shieldsExtraConfigs)
  )
}
