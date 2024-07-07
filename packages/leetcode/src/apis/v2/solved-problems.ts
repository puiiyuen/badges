import { OpenAPIRoute } from 'chanfana'
import { z } from 'zod'

import { solvedProblemsBadge } from '../../services/solved-problems'
import {
  difficultiesParam,
  shieldExtraConfigsParams,
  successfulResponse,
  usernameParam,
} from '../common'

export class SolvedProblems extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode'],
    request: {
      params: usernameParam,
      query: z
        .object({})
        .merge(difficultiesParam)
        .merge(shieldExtraConfigsParams),
    },
    responses: {
      200: successfulResponse,
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { difficulty, ...shieldExtraConfigs } = data.query
    const shield = await solvedProblemsBadge(
      username,
      difficulty,
      shieldExtraConfigs
    )
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
