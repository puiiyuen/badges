import { type Format } from 'badge-maker'

import { SubmissionStats } from '../adapters/leetcode'
import { difficultyConfigs } from './common'
import { ShieldBuilder } from './shields'
import {
  shieldExtraConfigsToQueryParams,
  ShieldsExtraConfigs,
} from './shields-extra-configs'

const acceptedRatesFormat = (
  acSubmissionNum: SubmissionStats,
  totalSubmissionNum: SubmissionStats
): string => {
  const acceptedRate = (
    (acSubmissionNum.submissions / totalSubmissionNum.submissions) *
    100
  ).toFixed(2)
  const format: Format = {
    label: 'Accepted',
    message: `${acSubmissionNum.difficulty} ${acceptedRate}%25`,
    color: difficultyConfigs[acSubmissionNum.difficulty].color,
  }
  const formatToString = `${format.label}-${format.message}-${format.color}`
  return formatToString
}

export const acceptedRatesBadge = async (
  username: string,
  difficulty: SubmissionStats['difficulty'],
  shieldsExtraConfigs?: Partial<ShieldsExtraConfigs>
): Promise<string> => {
  const shieldBuilder = new ShieldBuilder()
  return shieldBuilder.build(username, (leetCodeUser) => {
    const acSubmissionNum =
      leetCodeUser.acSubmissionNum[difficultyConfigs[difficulty].position]
    const totalSubmissionNum =
      leetCodeUser.totalSubmissionNum[difficultyConfigs[difficulty].position]
    return (
      acceptedRatesFormat(acSubmissionNum, totalSubmissionNum) +
      shieldExtraConfigsToQueryParams(shieldsExtraConfigs)
    )
  })
}
