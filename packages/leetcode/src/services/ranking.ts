import { type Format } from 'badge-maker'

import { ShieldBuilder } from './shields'
import { LeetCodeUser } from '../adapters/leetcode'

const rankingFormat = (ranking: LeetCodeUser['ranking']): string => {
  const format: Format = {
    label: 'Ranking',
    message: `${ranking}`,
    color: 'orange',
  }
  const formatToString = `${format.label}-${format.message}-${format.color}`
  return formatToString
}

export const rankingBadge = async (username: string): Promise<string> => {
  const shieldBuilder = new ShieldBuilder()
  return shieldBuilder.build(username, (leetCodeUser) =>
    rankingFormat(leetCodeUser.ranking)
  )
}
