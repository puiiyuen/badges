import { type Format } from 'badge-maker'

import { ShieldBuilder } from './shields'

const usernameFormat = (username: string): string => {
  const format: Format & { logo?: string } = {
    label: 'Leetcode',
    message: username,
    color: 'orange',
    logo: 'leetcode',
  }
  const formatToString = `${format.label}-${format.message}-${format.color}?logo=${format.logo}`
  return formatToString
}

export const usernameBadge = async (username: string): Promise<string> => {
  const shieldBuilder = new ShieldBuilder()
  return shieldBuilder.build(username, () => usernameFormat(username))
}
