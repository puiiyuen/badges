import { type Format } from 'badge-maker'

import { SubmissionStats } from '../adapters/leetcode'
import { difficultyConfigs } from './common'
import { ShieldBuilder } from './shields'

const submissionsFormat = (
  submissionNum: SubmissionStats,
  label: 'Accepted' | 'Total'
): string => {
  const format: Format = {
    label,
    message: `${submissionNum.difficulty} ${submissionNum.submissions}`,
    color: difficultyConfigs[submissionNum.difficulty].color,
  }
  const formatToString = `${format.label}-${format.message}-${format.color}`
  return formatToString
}

export const submissionsBadge = async (
  username: string,
  difficulty: SubmissionStats['difficulty'],
  accepted: boolean
): Promise<string> => {
  const shieldBuilder = new ShieldBuilder()
  return shieldBuilder.build(username, (leetCodeUser) => {
    const submissionNum = accepted
      ? leetCodeUser.acSubmissionNum[difficultyConfigs[difficulty].position]
      : leetCodeUser.totalSubmissionNum[difficultyConfigs[difficulty].position]
    const label = accepted ? 'Accepted' : 'Total'

    return submissionsFormat(submissionNum, label)
  })
}
