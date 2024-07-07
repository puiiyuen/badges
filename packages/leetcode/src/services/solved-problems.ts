import { type Format } from 'badge-maker'

import { QuestionCount, SubmissionStats } from '../adapters/leetcode'
import { difficultyConfigs } from './common'
import { ShieldBuilder } from './shields'
import {
  shieldExtraConfigsToQueryParams,
  ShieldsExtraConfigs,
} from './shields-extra-configs'

const solvedProblemsFormat = (
  acSubmissionNum: SubmissionStats,
  questionsCount: QuestionCount
): string => {
  const format: Format = {
    label: 'Solved',
    message: `${acSubmissionNum.difficulty} ${acSubmissionNum.count}/${questionsCount.count}`,
    color: difficultyConfigs[acSubmissionNum.difficulty].color,
  }
  const formatToString = `${format.label}-${format.message}-${format.color}`
  return formatToString
}

export const solvedProblemsBadge = async (
  username: string,
  difficulty: SubmissionStats['difficulty'],
  shieldsExtraConfigs?: Partial<ShieldsExtraConfigs>
): Promise<string> => {
  const shieldBuilder = new ShieldBuilder()
  return shieldBuilder.build(username, (leetCodeUser) => {
    const acSubmissionNum =
      leetCodeUser.acSubmissionNum[difficultyConfigs[difficulty].position]
    const questionsCount =
      leetCodeUser.allQuestionsCount[difficultyConfigs[difficulty].position]
    return (
      solvedProblemsFormat(acSubmissionNum, questionsCount) +
      shieldExtraConfigsToQueryParams(shieldsExtraConfigs)
    )
  })
}
