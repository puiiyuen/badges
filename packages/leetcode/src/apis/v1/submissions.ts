import { Bool, OpenAPIRoute } from 'chanfana'
import { z } from 'zod'

import { submissionsBadge } from '../../services/submissions'
import {
  difficultiesParamV1,
  shieldExtraConfigsParams,
  successfulResponse,
  usernameParam,
} from '../common'

export class Submissions extends OpenAPIRoute {
  schema = {
    tags: ['LeetCode v1'],
    request: {
      query: z
        .object({})
        .merge(difficultiesParamV1)
        .merge(
          z.object({
            accepted: Bool({
              default: false,
              required: false,
            }),
          })
        )
        .merge(shieldExtraConfigsParams),
      params: usernameParam,
    },
    responses: {
      200: successfulResponse,
    },
  }

  async handle() {
    const data = await this.getValidatedData<typeof this.schema>()
    const { username } = data.params
    const { difficulty, accepted, ...shieldExtraConfigs } = data.query
    const shield = await submissionsBadge(
      username,
      difficulty[0].toUpperCase() + difficulty.slice(1),
      accepted,
      shieldExtraConfigs
    )
    return new Response(shield, {
      headers: {
        'content-type': 'image/svg+xml',
      },
    })
  }
}
